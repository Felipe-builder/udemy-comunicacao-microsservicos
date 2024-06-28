import express from "express";

import userRoutes from './src/modules/user/routes/UserRoutes.js';
import ExceptionHandler from "./src/config/middleware/ExceptionHandler.js";
import tracing from "./src/config/tracing.js";

const app = express();
const env = process.env;
const PORT = env.PORT || 8080;


// db.createInitialData();
app.use(express.json());


app.get('/', (req, res) => {
  return res.status(200).json(getStatusResponse())
})
app.get('/api/status', (req, res) => {
  return res.status(200).json(getStatusResponse())
})

function getStatusResponse() {
  return {
    service: "Auth-API",
    status: "up",
    httpStatus: 200,
  }
}

app.use(tracing);

app.use(userRoutes);

app.use(ExceptionHandler)

app.listen(PORT, () => {
  console.info(`Server started successfully at port ${PORT}`)
})

