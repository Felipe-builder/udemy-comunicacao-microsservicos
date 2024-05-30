import { sendMessageToProductStockUpdateQueue } from '../../products/rabbitmq/producktStockUpdateSender.js'
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

}

export default new OrderController();