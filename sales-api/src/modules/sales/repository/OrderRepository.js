import Order from '../model/Order.js'

class OrderRepository {

  async create(order) {
    try {
      return await Order.create(order);
    } catch (error) {
      console.error(error.message);
      throw error;
    }
  }

  async findById(id) {
    try {
      return await Order.findById(id);
    } catch (error) {
      console.error(error.message);
      return null;
    }
  }

  async findAll() {
    try {
      return await Order.find()
    } catch (error) {
      console.error(error.message);
      return null;
    }
  }
}

export default new OrderRepository();