// config.js
const env = process.env;

 export const configs = {
  ENVIRONMENT: process.env.ENVIRONMENT || "development",
  DATABASE: {
    NAME: process.env.DB_NAME || "auth-db",
    USER: process.env.DB_USER || "admin",
    PASSWORD: process.env.DB_PASSWORD || "123456",
    HOST: process.env.DB_HOST || "localhost",
    PORT: process.env.DB_PORT || 5432,
  },
  API_SECRET: env.API_SECRET ||  "YXV0aC1hcGktc2VjcmV0LWRldi0xMjM0NTY=",
  // Adicione outras configurações conforme necessário
};
