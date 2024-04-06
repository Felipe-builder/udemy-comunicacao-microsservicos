// errorHandler.js
import CustomException from '../exceptions/CustomException.js';

const ExceptionHandler = (err, req, res, next) => {
 if (err instanceof CustomException) {
    // Trata erros personalizados
    return res.status(err.status).json({
      status: err.status,
      message: err.message,
    });
 } else if (err.name === 'SequelizeValidationError') {
    // Trata erros de validação do Sequelize
    const errors = err.errors.map(e => ({
      field: e.path, // Campo que falhou na validação
      message: e.message, // Mensagem de erro específica
    }));

    return res.status(400).json({
      status: 400,
      message: 'Validation failed',
      errors: errors,
    });
 } else {
    // Trata outros tipos de erros
    console.error(err.stack);
    return res.status(500).json({
      status: 500,
      message: 'An internal server error occurred.',
    });
 }
};

export default ExceptionHandler;
