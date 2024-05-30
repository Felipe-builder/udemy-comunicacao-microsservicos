import axios from "axios";
import { configs } from "../../../config/constants/secrets.js";
import * as httpStatus from '../../../config/constants/httpStatus.js'


class ProductCliente {

    async checkProductStock(products, token) {
        try {
            const headers = {
                Auhtorization: `Bearer ${token}`,
            }

            console.info(`Sending request to Product API with data: ${JSON.stringify(products)}`)
            axios
                .post(`${configs.PRODUCT_API_URL}/check-stock`, { headers})
                .then((res) => {
                    return true;
                })
                .catch((err) => {
                    console.error(err.response.message)
                    return false
                })
        } catch (error) {
            return false
        }
    }
}

export default new ProductCliente(); 