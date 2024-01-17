import { Module } from '@nestjs/common';
import { MongoConnection } from './persistence/providers/MongoConnection.providers';
import { RecipeProvider } from './persistence/providers/recipe.provider';

@Module({
  providers: [MongoConnection, RecipeProvider],
})
export class InfrastructureModule {}
