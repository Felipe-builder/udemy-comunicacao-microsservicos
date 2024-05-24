import amqp from 'amqplib/callback_api.js';

import {
    PRODUCT_STOCK_UPDATE_QUEUE,
    PRODUCT_STOCK_UPDATE_ROUTING_KEY,
    PRODUCT_TOPIC,
    SALES_CONFIRMATION_QUEUE,
    SALES_CONFIRMATION_ROUTING_KEY
} from './queue.js'

import { configs } from '../constants/secrets.js'

const HALF_SECOND = 500;

export async function connectRabbitMq() {
    amqp.connect(configs.RABBIT_MQ_URL, (error, connection) => {
        if (error) {
            throw error;
        }
        createQueue(connection, PRODUCT_STOCK_UPDATE_QUEUE, PRODUCT_STOCK_UPDATE_ROUTING_KEY, PRODUCT_TOPIC);
        createQueue(connection, SALES_CONFIRMATION_QUEUE, SALES_CONFIRMATION_ROUTING_KEY, PRODUCT_TOPIC);
        setTimeout(function () {
            connection.close();
        }, HALF_SECOND);
    });

    function createQueue(connection, queue, routingKey, topic) {
        connection.createChannel((error, channel) => {
            if (error) {
                throw error;
            }

            console.log(`Queue: ${queue}`);
            console.log(`Routing Key: ${routingKey}`);
            console.log(`Topic: ${topic}`);

            if (typeof queue !== 'string' || queue.length > 255) {
                throw new TypeError(`Invalid queue: ${queue}`);
            }
            if (typeof routingKey !== 'string' || routingKey.length > 255) {
                throw new TypeError(`Invalid routingKey: ${routingKey}`);
            }
            if (typeof topic !== 'string' || topic.length > 255) {
                throw new TypeError(`Invalid topic: ${topic}`);
            }

            channel.assertExchange(topic, 'topic', { durable: true });
            channel.assertQueue(queue, { durable: true });
            channel.bindQueue(queue, topic, routingKey);
        });
    }
}
