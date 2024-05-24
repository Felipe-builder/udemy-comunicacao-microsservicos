import mongoose, { mongo } from 'mongoose';

import { configs } from '../constants/secrets.js';

export function connectMongoDb() {
    mongoose.connect(configs.MONGO_DB_URL);

    mongoose.connection.on('connected', function () {
        console.info('The application connected to MongoDB sucessfuly!');
    })
    mongoose.connection.on('error', function () {
        console.error('The application does not connect to MongoDB sucessfuly!');
    })
}