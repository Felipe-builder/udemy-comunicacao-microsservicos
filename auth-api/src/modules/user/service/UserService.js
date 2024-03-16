import UserRepository from '../repository/UserRepository.js'
import * as httpStatus from '../../../config/constants/httpStatus.js'

import UserException from '../exceptions/UserException.js';

class UserService {
  async findById(id) {
    try {
      this.validateDataRequest(id);
      const user = await UserRepository.findById(id);
      this.validateUserNotfound(user);
      return {
        status: httpStatus.SUCCESS,
        user: {
          id: user.id,
          name: user.name,
          email: user.email,
        }
      }
    } catch (error) {
      console.error(error.message);
      return {
        status: error.status ? error.status : httpStatus.INTERNAL_SERVER_ERROR,
        message: error.message
      };
    }
  }

  async findByEmail(email) {
    try {
      this.validateDataRequest(email);
      const user =  await UserRepository.findByEmail(email);
      this.validateUserNotfound(user);
      return {
        status: httpStatus.SUCCESS,
        user: {
          id: user.id,
          name: user.name,
          email: user.email,
        }
      }
    } catch (error) {
      console.error(error.message);
      return {
        status: error.status ? error.status : httpStatus.INTERNAL_SERVER_ERROR,
        message: error.message
      };
    }
  }

  validateDataRequest(data) {
    if (!data) {
      throw new UserException(httpStatus.BAD_REQUEST, "User was not informed a data.")
    }
  }

  validateUserNotfound(user) {
    if (!user) {
      throw new UserException(httpStatus.NOT_FOUND, "User was not found.")
    }
  }
}

export default new UserService();