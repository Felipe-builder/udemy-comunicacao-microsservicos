import axios from "axios";
import { configs } from "../../../config/constants/secrets.js";


class ProductCliente {
    async checkProductStock(order, token, transactionid) {
        try {
            const headers = {
                Authorization: token,
                transactionid
            }
            let response = {};
            const { products } = order;
            console.info(`Sending request to Product API with data: ${JSON.stringify(products)} and transactionID ${transactionid}`)
            await axios
                .post(`${configs.PRODUCT_API_URL}/check-stock`, { products }, { headers })
                .then((res) => {
                    console.info(`Success response from Product-API. TransactionID: ${transactionid}`)
                    response = null;
                })
                .catch((err) => {
                    console.error(`Error response from Product-API. TransactionID: ${transactionid}`)
                    response = err.response.data;
                })
            return response
        } catch (error) {
            console.error(`Error response from Product-API. TransactionID: ${transactionid}`)
            return false
        }
    }
}

export default new ProductCliente(); 