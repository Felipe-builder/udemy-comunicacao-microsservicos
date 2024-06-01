import { Router } from 'express';

import OrderController from "../controller/OrderController.js";
import checkToken from '../../../config/auth/checkToken.js';

const router = new Router();

router.use(checkToken);

router.get('/api/teste', OrderController.teste);
router.get('/api/order/:id', OrderController.findById);
router.post('/api/order/create', OrderController.createOrder);


export default router;