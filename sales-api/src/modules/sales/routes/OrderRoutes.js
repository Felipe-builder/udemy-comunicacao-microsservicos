import { Router } from 'express';

import OrderController from "../controller/OrderController.js";
import checkToken from '../../../config/auth/checkToken.js';

const router = new Router();

router.use(checkToken);

router.post('/api/order/create', OrderController.createOrder);
router.get('/api/order/:id', OrderController.findById);
router.get('/api/order', OrderController.findAll);
router.get('/api/order/product/:productId', OrderController.findByProductId);


export default router;