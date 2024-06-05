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
      const { id } = req.params;
      const response = await OrderService.findById(id);
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
      const { productId } = req.params;
      const response = await OrderService.findByProductId(productId);
      return res.status(200).json(response)
    } catch (error) {
      next(error)
    }
  }
}

export default new OrderController();