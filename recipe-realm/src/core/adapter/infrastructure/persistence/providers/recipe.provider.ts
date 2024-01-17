import { Provider } from '@nestjs/common';
import { Connection } from 'mongoose';
import constants from '../../constants';
import { RecipeSchema } from '../schema/recipe.schema';

export const RecipeProvider: Provider = {
  provide: constants.recipeProvider,
  useFactory: (connection: Connection) =>
    connection.model('Recipe', RecipeSchema),
  inject: [constants.mongoProvider],
};
