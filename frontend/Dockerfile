FROM node:alpine3.14

WORKDIR /app

COPY package*.json /app

RUN npm ci

COPY . .

CMD ["npm", "run", "dev"]
