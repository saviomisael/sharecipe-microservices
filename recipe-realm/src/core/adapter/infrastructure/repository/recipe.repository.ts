import { Inject, Injectable } from '@nestjs/common';
import { Model } from 'mongoose';
import { Recipe } from 'src/core/domain/entities/recipe.entity';
import { IRecipeRepository } from 'src/core/domain/repository/irecipe.repository';
import constants from '../constants';
import { RecipeDocument } from '../persistence/document/recipe.document';

@Injectable()
export class RecipeRepository implements IRecipeRepository {
  constructor(
    @Inject(constants.recipeProvider)
    private recipeModel: Model<RecipeDocument>,
  ) {}

  async createRecipe(recipe: Recipe): Promise<void> {
    const recipeToSave = new this.recipeModel(recipe);
    await recipeToSave.save();
  }
}
