export interface RecipeDocument extends Document {
  readonly name: string;
  readonly yieldCapacity: number;
  readonly minutesToCook: number;
  readonly createdAt: Date;
  readonly isPrivate: boolean;
  readonly ingredients: string[];
  readonly steps: Array<{ item: string; orderPosition: number }>;
  readonly chef: { username: string; fullName: string };
}
