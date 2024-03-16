import bcrypt from 'bcrypt';
import jwt from 'jsonwebtoken';

import UserRepository from '../repository/UserRepository.js'
import * as httpStatus from '../../../config/constants/httpStatus.js'

import * as secrets  from '../../../config/constants/secrets.js'

import CustomException from '../exceptions/CustomException.js';

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

  async findByEmail(email, authUser) {
    try {
      this.validateDataRequest(email);
      const user = await UserRepository.findByEmail(email);
      this.validateUserNotfound(user);
      this.validateAuthenticatedUser(user, authUser)

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

  async create(body) {
    try {
      const { name, email, password } = body;
      [name, email, password].forEach(data => this.validateDataRequest(data));

      const user = await UserRepository.findByEmail(email);
      
      if (user) {
        throw new CustomException(httpStatus.BAD_REQUEST, "Email already exists")
      }
      const encryptPassword = await bcrypt.hash(password, 10)

      const createdUser = await UserRepository.create({ name, email, password: encryptPassword})
      return {
        status: httpStatus.SUCCESS,
        user: {
          id: createdUser.id,
          name: createdUser.name,
          email: createdUser.email,
        },
      }
    } catch (error) {
      console.error(error.message);
      return {
        status: error.status ? error.status : httpStatus.INTERNAL_SERVER_ERROR,
        message: error.message
      };
    }
  }


  async getAccessToken(email, password) {
    try {
      this.validateAccessTokenData(email, password);
      const user = await UserRepository.findByEmail(email);
      this.validateUserNotfound(user);
      await this.validatePassword(password, user.password)
      const authUser =  {
        id: user.id,
        name: user.name,
        email: user.email,
      }
      const accessToken = jwt.sign({ authUser }, secrets.API_SECRET, {expiresIn: '1d'})
      return {
        status: httpStatus.SUCCESS,
        accessToken,
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
      throw new CustomException(httpStatus.BAD_REQUEST, "User was not informed a data.")
    }
  }
  
  validateUserNotfound(user) {
    if (!user) {
      throw new CustomException(httpStatus.NOT_FOUND, "User was not found.")
    }
  }
  
  validateAccessTokenData(email, password) {
    if (!email || !password) {
      throw new CustomException(
        httpStatus.UNAUTHORIZED,
        "Email and password must be informed."
      )
    }
  }

  validateAuthenticatedUser(user, authUser) {
    if(!authUser || user.id !== authUser.id) {
      throw new CustomException(
        httpStatus.FORBIDEN,
        "You cannot see this user data."
      )
    }
  }

  async validatePassword(password, hasPassword) {
    if (!await bcrypt.compare(password, hasPassword)) {
      throw new CustomException(httpStatus.UNAUTHORIZED, "Password or email doens't match.")
    }
  }

}

export default new UserService();