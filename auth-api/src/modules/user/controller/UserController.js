import UserService from '../service/UserService.js';

class UserController {

  async getAccessToken(req, res) {
    const { email, password } = req.body;
    const result = await UserService.getAccessToken(email, password)
    return res.status(result.status).json(result)
  }

  

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