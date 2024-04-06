import Sequelize from 'sequelize';

import { configs } from '../constants/configs.js'

const sequelize = new Sequelize("auth-db", "admin", "123456", {
  host: "localhost",
  dialect: "postgres",
  quoteIdentifiers: false,
  define: {
    syncOnAssociation: true,
    timestamps: false,
    undescored: true,
    undescoredAll: true,
    freezeTableName: true,
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