import UserService from '../service/UserService.js';

class UserController {
  async findById(req, res) {
      const { id } = req.params;
      const user = await UserService.findById(id);
      return res.status(user.status).json(user)
  }

  async findByEmail(req, res) {
      const { email } = req.params;
      const user = await UserService.findByEmail(email);
      return res.status(user.status).json(user)
  }
}

export default new UserController();