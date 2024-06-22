// config.js
const env = process.env;

export const configs = {
  ENVIRONMENT: process.env.ENVIRONMENT || "development",
  DATABASE: {
    DB_NAME: process.env.DB_NAME || "auth-db",
    DB_USER: process.env.DB_USER || "admin",
    DB_PASSWORD: process.env.DB_PASSWORD || "123456",
    DB_HOST: process.env.DB_HOST || "localhost",
    DB_PORT: process.env.DB_PORT || 5432,
  },
  PORT: env.PORT || '8080',
  API_SECRET: env.API_SECRET || "YXV0aC1hcGktc2VjcmV0LWRldi0xMjM0NTY=",
  // Adicione outras configurações conforme necessário
};
