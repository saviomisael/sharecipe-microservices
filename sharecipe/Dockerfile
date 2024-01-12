FROM node:20.10.0

WORKDIR sharecipe

RUN npm i -g @angular/cli@17.0.9

COPY ./package*.json .

RUN npm ci

COPY . .

RUN ng build

EXPOSE 4200

CMD ["node", "./dist/sharecipe/server/server.mjs"]
