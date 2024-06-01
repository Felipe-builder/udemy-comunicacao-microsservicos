import { sendMessageToProductStockUpdateQueue } from '../../products/rabbitmq/producktStockUpdateSender.js'
import OrderService from '../service/OrderService.js';
class OrderController {

  async teste(req, res, next) {
    const data = [
      {
        productId: 1001,
        quantity: 2,
      },
      {
        productId: 1002,
        quantity: 3,
      },
      {
        productId: 1003,
        quantity: 2,
      }
    ]
    try {
      sendMessageToProductStockUpdateQueue(data);
      return res.status(200).json({
        "message": "Teste feito com sucesso!",
        "data": data
      })
    } catch (error) {
      next(error)
    }
  }

  async createOrder(req, res, next) {
    try {
      let order = await OrderService.create(req);
      return res.status(200).json(order)
    } catch (error) {
      next(error)
    }
  }

  async findById(req, res, next) {
    try {
      const { id } = req.params;
      const order = await OrderService.findById(id);
      return res.status(200).json(order)
    } catch (error) {
      next(error)
    }
  }
}

export default new OrderController();