const env = process.env;


export const configs = {
  ENVIRONMENT: process.env.ENVIRONMENT || "development",
  MONGO_DB_URL: process.env.MONGO_DB_URL || "mongodb://admin:123456@localhost:27017/",
  API_SECRET: env.API_SECRET || "YXV0aC1hcGktc2VjcmV0LWRldi0xMjM0NTY=",
  RABBIT_MQ_URL: env.RABBIT_MQ_URL || "amqp://localhost:5672",
  PRODUCT_API_URL: env.PRODUCT_API_URL || "http://localhost:8081/api/product",

  // Adicione outras configurações conforme necessário
};
