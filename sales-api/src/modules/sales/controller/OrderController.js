import { sendMessageToProductStockUpdateQueue } from '../../products/rabbitmq/producktStockUpdateSender.js'
import OrderService from '../service/OrderService.js';
class OrderController {

  async createOrder(req, res, next) {
    try {
      const response = await OrderService.create(req);
      return res.status(200).json(response)
    } catch (error) {
      next(error)
    }
  }

  async findById(req, res, next) {
    try {
      const response = await OrderService.findById(req);
      return res.status(200).json(response)
    } catch (error) {
      next(error)
    }
  }

  async findAll(req, res, next) {
    try {
      const response = await OrderService.findAll();
      return res.status(200).json(response)
    } catch (error) {
      next(error)
    }
  }

  async findByProductId(req, res, next) {
    try {
      const response = await OrderService.findByProductId(req);
      return res.status(200).json(response)
    } catch (error) {
      next(error)
    }
  }
}

export default new OrderController();