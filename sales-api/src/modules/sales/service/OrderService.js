import OrderRepository from '../repository/OrderRepository.js'

import { sendMessageToProductStockUpdateQueue } from '../../products/rabbitmq/producktStockUpdateSender.js'

import { PENDING, ACCEPTED, REJECTED } from '../model/enums/OrderStatus.js'

import * as httpStatus from '../../../config/constants/httpStatus.js'
import { configs } from '../../../config/constants/secrets.js'

import CustomException from '../../../config/exceptions/CustomException.js';

class OrderService {

  async create(req) {
    try {
      let orderData = req.body;
      this.validateOrderData(orderData);
      const { authUser } = req;
      let order = {
        status: PENDING,
        user: authUser,
        products: orderData,
        createdAt: new Date(),
        updatedAt: new Date(),
      }

      await this.validateProductStock(order);

      let createdOrder = await OrderRepository.create(order);
      sendMessageToProductStockUpdateQueue(createdOrder.products);

      return {
        status: httpStatus.SUCCESS,
        createdOrder
      }
    } catch (error) {
      console.error(error.message);
      throw error
    }
  }

  async updatedOrder(orderMessage) {
    try {
      const order = JSON.parse(orderMessage);
      if ( order.salesId && order.status) {
        let existingOrder = await OrderRepository.findById(order.salesId);
        if ( existingOrder && order.status !== existingOrder.status) {
          existingOrder.status = order.status;
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
      this.validateDataRequest(id);
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

  async validateProductStock(order) {
    let stockIsOut = true;

    if (stockIsOut) {
      throw new CustomException(httpStatus.BAD_REQUEST, 'The stock is out for the products')
    }
  }
}

export default new OrderService();