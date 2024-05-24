import express from "express";

import { connectMongoDb } from "./src/config/db/mongoDbConfig.js";
import Order from "./src/modules/order/model/Order.js";
import orderRoutes from './src/modules/order/router/OrderRoutes.js';
import ExceptionHandler from "./src/config/middleware/ExceptionHandler.js";
import { connectRabbitMq } from './src/config/rabbitmq/rabbitConfig.js'

const app = express();
const env = process.env;
const PORT = env.PORT || 8082;

connectMongoDb();
connectRabbitMq()

app.use(express.json())

app.get('/api/status', async (req, res) => {
  let test = await Order.find();
  console.log(test)
  return res.status(200).json({
    service: "Sales-API",
    status: "up",
    httpStatus: 200,
  })
})

app.use(orderRoutes);

app.use(ExceptionHandler)


app.listen(PORT, () => {
  console.info(`Server started successfully at port ${PORT}`)
})

