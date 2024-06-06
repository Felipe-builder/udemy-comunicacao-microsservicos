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
      const { transactionid, serviceid, authorization } = req.headers;
      const orderData = req.body;
      const { authUser } = req;
      console.log(
        `Request to POST new order with data ${JSON.stringify(req.body)} | transactionID: ${transactionid} | serviceID: ${serviceid}`
      );
      this.validateOrderData(orderData);
      
      let order = this.createInitialOrderData(orderData, authUser, transactionid, serviceid)
      
      await this.validateProductStock(order, authorization, transactionid);
      let createdOrder = await OrderRepository.create(order);
      this.sendMessage(createdOrder, transactionid);
      const response = {
        status: httpStatus.SUCCESS,
        createdOrder
      }
      console.log(
        `Response to POST new order with data ${JSON.stringify(response)} | ${transactionid} | serviceID: ${serviceid}`
      );
      return response 
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
        console.warn(`The order message was not complete. TransactionID: ${orderMessage.transactionid}`)
      }
      return {
        status: httpStatus.SUCCESS,
      }
    } catch (error) {
      console.error(error.message);
      throw error
    }
  }


  async findById(req) {
    try {
      const { transactionid, serviceid } = req.headers;
      const { id } = req.params;
      console.log(
        `Request to GET find order by id ${id} | transactionID: ${transactionid} | serviceID: ${serviceid}`
      );
      this.validateDataRequest({ 'id': id });
      const order = await OrderRepository.findById(id);
      this.validateOrderNotfound(order);
      const response = {
        status: httpStatus.SUCCESS,
        order
      }
      console.log(
        `Response to GET find order by id with data ${JSON.stringify(response)} | ${transactionid} | serviceID: ${serviceid}`
      );
      return response
    } catch (error) {
      console.error(error.message);
      throw error
    }
  }

  async findByProductId(req) {
    try {
      const { transactionid, serviceid } = req.headers;
      const { productId } = req.params;
      console.log(
        `Request to GET find order by product ${productId} | transactionID: ${transactionid} | serviceID: ${serviceid}`
      );

      this.validateDataRequest({ 'productId': productId });
      const orders = await OrderRepository.findByProductId(productId);
      this.validateOrderNotfound(orders);
      
      const response = {
        status: httpStatus.SUCCESS,
        salesIds: orders.map((order) => {return order._id})
      }
      console.log(
        `Response to GET find order by product with data ${JSON.stringify(response)} | ${transactionid} | serviceID: ${serviceid}`
      );
      return response
    } catch (error) {
      console.error(error.message);
      throw error
    }
  }

  async findAll() {
    try {
      const { transactionid, serviceid } = req.headers;
      console.log(
        `Request to GET find all orders | transactionID: ${transactionid} | serviceID: ${serviceid}`
      );
      const orders = await OrderRepository.findAll();
      const response = {
        status: httpStatus.SUCCESS,
        orders
      }
      console.log(
        `Response to GET find all orders with data ${JSON.stringify(response)} | ${transactionid} | serviceID: ${serviceid}`
      );
      return response
    } catch (error) {
      console.error(error.message);
      throw error
    }
  }

  createInitialOrderData(orderData, authUser, transactionid, serviceid) {
    return {
      status: PENDING,
      user: authUser,
      products: orderData.products,
      transactionid,
      serviceid,
      createdAt: new Date(),
      updatedAt: new Date(),
    }
  }

  sendMessage(createdOrder, transactionid) {
    const message = {
      salesId: createdOrder.id,
      products: createdOrder.products,
      transactionid
    }
    sendMessageToProductStockUpdateQueue(message);
  }

  validateDataRequest(data) {
    const keys = Object.keys(data)
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

  async validateProductStock(order, token, transactionid) {
    let stockIsOut = await ProductClient.checkProductStock(order, token, transactionid);

    if (stockIsOut) {
      throw new CustomException(stockIsOut.status || httpStatus.BAD_REQUEST, stockIsOut.message || 'The stock is out for the products')
    }
  }
}

export default new OrderService();