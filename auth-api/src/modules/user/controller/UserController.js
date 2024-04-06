import UserService from '../service/UserService.js';

class UserController {

  async getAccessToken(req, res, next) {
    try {
      const { email, password } = req.body;
      const result = await UserService.getAccessToken(email, password)
      return res.status(result.status).json(result)
    } catch (error) {
      next(error)
    }
  }

  async createUser(req, res, next) {
    try {
      const result = await UserService.create(req.body)
      return res.status(result.status).json(result)
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