import amqp, { connect } from 'amqplib/callback_api.js';

import { configs } from '../../../config/constants/secrets.js';

import {
    SALES_CONFIRMATION_QUEUE,
} from '../../../config/rabbitmq/queue.js'
import OrderService from '../service/OrderService.js';

export function listenToSalesConfirmationQueue() {
    amqp.connect(configs.RABBIT_MQ_URL, (error, connection) => {
        if (error) {
            throw error;
        }
        console.info("Listening to Sales Cofirmation Queue...")
        connection.createChannel((error, channel) => {
            if (error) {
                throw error;
            }
            channel.consume(
                SALES_CONFIRMATION_QUEUE,
                (message) => {
                    console.info(`Recieving message fro queue: ${message.content.toString()}`);
                    OrderService.updateOrder(message);
                }, {
                    noAck: true,
                }
            )
        })
    });
}