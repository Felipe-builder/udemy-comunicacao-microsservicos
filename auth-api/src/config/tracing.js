import { v4 as uuidv4 } from 'uuid';

import { BAD_REQUEST  } from '../config/constants/httpStatus.js';
import CustomException from './exceptions/CustomException.js';

export default (req, res, next) => {
    let { transactionid } = req.headers;
    if (!transactionid) {
        throw new CustomException(BAD_REQUEST, "The transactionid header is required.");
    }
    req.headers.serviceid = uuidv4();
    return next()
}