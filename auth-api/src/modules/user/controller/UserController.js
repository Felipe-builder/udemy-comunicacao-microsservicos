import UserService from '../service/UserService.js';

class UserController {

  async getAccessToken(req, res, next) {
    try {
      const { transactionid, serviceid } = req.headers;
      console.log(
        `Request to POST login with data ${JSON.stringify(req.body)} | transactionID: ${transactionid} | serviceID: ${serviceid}`
      );
      const { email, password } = req.body;
      const response = await UserService.getAccessToken(email, password)
      console.log(
        `Response to POST login with data ${JSON.stringify(response)} | ${transactionid} | serviceID: ${serviceid}`
      );
      return res.status(response.status).json(response)
    } catch (error) {
      next(error)
    }
  }

  async createUser(req, res, next) {
    try {
      const response = await UserService.create(req.body)
      return res.status(response.status).json(response)
    } catch (error) {
      next(error)
    }
  }

  async findById(req, res, next) {
    try {
      const { id } = req.params;
      const user = await UserService.findById(id);
      return res.status(user.status).json(user)
    } catch (error) {
      next(error)
    }
  }

  async findByEmail(req, res, next) {
    try {
      const { email } = req.params;
      const { authUser } = req;
      const user = await UserService.findByEmail(email, authUser);
      return res.status(user.status).json(user)
    } catch (error) {
      next(error)
    }
  }
}

export default new UserController();