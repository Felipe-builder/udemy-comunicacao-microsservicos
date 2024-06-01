import axios from "axios";
import { configs } from "../../../config/constants/secrets.js";


class ProductCliente {
    async checkProductStock(order, token) {
        try {
            const headers = {
                Authorization: token,
            }
            let response = {};
            const { products } = order;
            console.info(`Sending request to Product API with data: ${JSON.stringify(products)}`)
            await axios
                .post(`${configs.PRODUCT_API_URL}/check-stock`, { products }, { headers })
                .then((res) => {
                    console.info(res.data)
                    response = null;
                })
                .catch((err) => {
                    console.error(err.response.data)
                    response = err.response.data;
                })
            return response
        } catch (error) {
            return false
        }
    }
}

export default new ProductCliente(); 