
class OrderController {

  async teste(req, res, next) {
    try {
      return res.status(200).json({
        "message": "Teste feito com sucesso!",
        "ping": "pong"
      })
    } catch (error) {
      next(error)
    }
  }

}

export default new OrderController();