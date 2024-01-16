import { Module } from '@nestjs/common';
import { MongoConnection } from './persistence/MongoConnection.providers';

@Module({
  providers: [MongoConnection],
})
export class InfrastructureModule {}
