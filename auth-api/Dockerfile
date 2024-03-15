FROM node:20.11-alpine3.18
# node:20.11
WORKDIR /app
COPY package*.json ./

RUN yarn
COPY . .
EXPOSE 8080

CMD [ "node", "app.js" ]