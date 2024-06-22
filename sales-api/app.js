import express from "express";
import dotenv from 'dotenv';

dotenv.config();

import { connectMongoDb } from "./src/config/db/mongoDbConfig.js";
import orderRoutes from './src/modules/sales/routes/OrderRoutes.js';
import ExceptionHandler from "./src/config/middleware/ExceptionHandler.js";
import { connectRabbitMq } from './src/config/rabbitmq/rabbitConfig.js'
import tracing from "./src/config/tracing.js";
const app = express();
const env = process.env;
const PORT = env.PORT || 8082;
const CONTAINER_ENV = "container";
const THREE_MINUTES = 15000;

startApplication();

async function startApplication() {
  if (CONTAINER_ENV === env.NODE_ENV) {
    console.info("Waiting for RabbitMQ and MongoDB containers to start...");
    setInterval(() => {
      connectMongoDb();
      connectRabbitMq();
    }, THREE_MINUTES);
  } else {
    connectMongoDb();
    connectRabbitMq();
  }
}

app.use(express.json())

app.get("/", (req, res) => {
  return res.status(200).json(getOkResponse());
});

app.get("/api/status", (req, res) => {
  return res.status(200).json(getOkResponse());
});

function getOkResponse() {
  return {
    service: "Sales-API",
    status: "up",
    httpStatus: 200,
  }
}

app.use(tracing)

app.use(orderRoutes);
app.use(ExceptionHandler)


app.listen(PORT, () => {
  console.info(`Server started successfully at port ${PORT}`)
})

