FROM node:20

ENV PORT 8002

ENV DBPASSWD sharecipe

ENV DBUSER sharecipe

ENV DBNAME RecipeRealm

WORKDIR /recipe-realm

COPY package*.json /

RUN npm ci

COPY . .

RUN npm run build

EXPOSE 8002

CMD [ "node", "dist/main.js" ]