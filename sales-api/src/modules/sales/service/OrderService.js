import bcrypt from 'bcrypt';
import jwt from 'jsonwebtoken';

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
    if (!data || !data.products) {
      throw new CustomException(httpStatus.NOT_FOUND, "The Order was not found")
    }
  }
}

export default new OrderService();