import { Chef } from './chef.entity';
import { Step } from './step.entity';

export class Recipe {
  constructor(
    public readonly chef: Chef,
    public readonly isPrivate: boolean,
    public readonly ingredients: string[],
    public readonly steps: Step[],
    public readonly createdAt: Date,
    public readonly minutesToCook: number,
    public readonly yieldCapacity: number,
    public readonly name: string,
  ) {}
}
