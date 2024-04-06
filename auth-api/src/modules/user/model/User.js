import Sequelize from 'sequelize';

import sequelize from '../../../config/db/dbConfig.js';

const User = sequelize.define("user", {
  id: {
    type: Sequelize.UUID,
    primaryKey: true,
    defaultValue: Sequelize.UUIDV4, // Define o valor padrão como UUID
    allowNull: false
  },
  name: {
    type: Sequelize.STRING,
    allowNull: false,
  },
  email: {
    type: Sequelize.STRING,
    allowNull: false,
    unique: true,
    validate: {
      isEmail: true, // Valida se o valor é um e-mail válido
      notNull: true, // Garante que o e-mail não seja nulo
      notEmpty: true, // Garante que o e-mail não esteja vazio
      // Adiciona uma mensagem de erro personalizada para a restrição de unicidade
      isUnique: async function (value) {
        const user = await User.findOne({ where: { email: value } });
        if (user) {
          throw new Error('Email address already in use!');
        }
      }
    }
  },
  password: {
    type: Sequelize.STRING,
    allowNull: false
  }
}, {});

export default User;
