FROM node:18.0.0-alpine3.14

WORKDIR /frontend

COPY . .

RUN yarn 

EXPOSE 3000

CMD ["yarn", "dev"]