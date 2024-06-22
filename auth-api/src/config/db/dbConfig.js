import Sequelize from 'sequelize';

import { configs } from '../constants/configs.js'

const { DB_HOST, DB_NAME, DB_PASSWORD, DB_PORT, DB_USER } = configs.DATABASE

const sequelize = new Sequelize(DB_NAME, DB_USER, DB_PASSWORD, {
  host: DB_HOST,
  dialect: "postgres",
  quoteIdentifiers: false,
  define: {
    syncOnAssociation: true,
    timestamps: false,
    undescored: true,
    undescoredAll: true,
    freezeTableName: true,
  },
  pool: {
    acquire: 180000
  }
});



sequelize
  .authenticate()
  .then(() => {
    console.info('Connection has been established!');
    // Verifica se o ambiente é de desenvolvimento antes de sincronizar
    if (configs.ENVIRONMENT === 'development') {
      return sequelize.sync({ alter: true }); // Use alter: true para atualizar a estrutura existente
    }
  })
  .then(() => {
    console.info('Database synchronization completed!');
  })
  .catch((error) => {
    console.error('Unable to connect to the database');
    console.error(error.message);
  });

export default sequelize;