import express from "express";

import userRoutes from './src/modules/user/routes/UserRoutes.js';
import ExceptionHandler from "./src/config/middleware/ExceptionHandler.js";
import tracing from "./src/config/tracing.js";

const app = express();
const env = process.env;
const PORT = env.PORT || 8080;


// db.createInitialData();
app.use(tracing);
app.use(express.json())

app.get('/api/status', (req, res) => {
  return res.status(200).json({
    service: "Auth-API",
    status: "up",
    httpStatus: 200,
  })
})

app.use(userRoutes);

app.use(ExceptionHandler)

app.listen(PORT, () => {
  console.info(`Server started successfully at port ${PORT}`)
})

