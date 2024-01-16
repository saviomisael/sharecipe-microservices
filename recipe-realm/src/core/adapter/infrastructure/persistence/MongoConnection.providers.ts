import * as mongoose from 'mongoose';

export const MongoString = `mongodb://${process.env.DBUSER}:${process.env.DBPASSWD}@localhost:27017/${process.env.DBNAME}?authSource=admin`;

export const MongoConnection = {
  provide: 'MongoConnection',
  useFactory: (): Promise<typeof mongoose> => mongoose.connect(MongoString),
};
