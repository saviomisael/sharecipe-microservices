import { Recipe } from '../entities/recipe.entity';

export interface IRecipeRepository {
  createRecipe(recipe: Recipe): Promise<void>;
}
