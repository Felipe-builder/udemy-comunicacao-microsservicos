import jwt from 'jsonwebtoken';
import { promisify } from 'util';

import { configs }  from '../secrets/secrets.js';
import * as httpStatus from '../constants/httpStatus.js'

import CustomException from '../exceptions/CustomException.js';

const emptySpace = ' ';


export default async (req, res, next) => {
  try {
    const { authorization } = req.headers;
    if (!authorization) {
      throw new CustomException(httpStatus.UNAUTHORIZED, "Access token was not informed.")
    }

    let accessToken = authorization;
    if (accessToken.includes(emptySpace)) {
      accessToken = accessToken.split(emptySpace)[1]
    } 
    
    const decoded = await promisify(jwt.verify)(
      accessToken,
      configs.API_SECRET
    );

    req.authUser = decoded.authUser;
    return next()
  } catch (error) {
    console.error(error.message);
    const JwtStatusCode = error instanceof jwt.JsonWebTokenError ? httpStatus.UNAUTHORIZED : httpStatus.INTERNAL_SERVER_ERROR;
    next(
      new CustomException(
        error.status || JwtStatusCode,
        error.message
      )
    );
  }

}