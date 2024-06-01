import OrderRepository from '../repository/OrderRepository.js'

import { sendMessageToProductStockUpdateQueue } from '../../products/rabbitmq/producktStockUpdateSender.js'

import { PENDING, ACCEPTED, REJECTED } from '../model/enums/OrderStatus.js'

import * as httpStatus from '../../../config/constants/httpStatus.js'
import { configs } from '../../../config/constants/secrets.js'

import CustomException from '../../../config/exceptions/CustomException.js';
import ProductClient from '../../products/client/ProductClient.js'

class OrderService {

  async create(req) {
    try {
      const orderData = req.body;
      this.validateOrderData(orderData);

      const { authUser } = req;
      const { authorization } = req.headers;
      let order = this.createInitialOrderData(orderData, authUser)

      await this.validateProductStock(order, authorization);
      let createdOrder = await OrderRepository.create(order);
      this.sendMessage(createdOrder);

      return {
        status: httpStatus.SUCCESS,
        createdOrder
      }
    } catch (error) {
      console.error(error.message);
      throw error
    }
  }

  async updateOrder(orderMessage) {
    try {
      const order = JSON.parse(orderMessage);
      if (order.salesId && order.status) {
        let existingOrder = await OrderRepository.findById(order.salesId);
        if (existingOrder && order.status !== existingOrder.status) {
          existingOrder.status = order.status;
          existingOrder.updatedAt = new Date();
          await OrderRepository.create(existingOrder)
        }
      } else {
        console.warn('The order message was not complete.')
      }
      return {
        status: httpStatus.SUCCESS,
      }
    } catch (error) {
      console.error(error.message);
      throw error
    }
  }


  async findById(id) {
    try {
      this.validateDataRequest({ 'id': id });
      const order = await OrderRepository.findById(id);
      this.validateOrderNotfound(order);
      return {
        status: httpStatus.SUCCESS,
        order
      }
    } catch (error) {
      console.error(error.message);
      throw error
    }
  }

  async findAll() {
    try {
      const orders = await OrderRepository.findAll();

      return {
        status: httpStatus.SUCCESS,
        orders
      }
    } catch (error) {
      console.error(error.message);
      throw error
    }
  }

  createInitialOrderData(orderData, authUser) {
    return {
      status: PENDING,
      user: authUser,
      products: orderData.products,
      createdAt: new Date(),
      updatedAt: new Date(),
    }
  }

  sendMessage(createdOrder) {
    const message = {
      salesId: createdOrder.id,
      products: createdOrder.products
    }
    sendMessageToProductStockUpdateQueue(message);
  }

  validateDataRequest(data) {
    const keys = Object.keys(data)
    console.log(keys);
    console.log(data);
    if (!data) {
      throw new CustomException(httpStatus.BAD_REQUEST, `The '${keys}' must be informed`)
    }
  }


  validateOrderData(data) {
    if (!data || !data.products) {
      throw new CustomException(httpStatus.BAD_REQUEST, "The products must be informed")
    }
  }

  validateOrderNotfound(data) {
    if (!data) {
      throw new CustomException(httpStatus.NOT_FOUND, "The Order was not found")
    }
  }

  async validateProductStock(order, token) {
    let stockIsOut = await ProductClient.checkProductStock(order, token);

    if (stockIsOut) {
      throw new CustomException(stockIsOut.status || httpStatus.BAD_REQUEST, stockIsOut.message || 'The stock is out for the products')
    }
  }
}

export default new OrderService();