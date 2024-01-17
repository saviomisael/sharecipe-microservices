import * as mongoose from 'mongoose';

const ChefSchema = new mongoose.Schema({
  username: {
    type: String,
    required: true,
  },
  fullName: {
    type: String,
    required: true,
  },
});

const StepSchema = new mongoose.Schema({
  item: {
    type: String,
    required: true,
  },
  orderPosition: {
    type: Number,
    required: true,
  },
});

export const RecipeSchema = new mongoose.Schema({
  chef: {
    type: ChefSchema,
    required: true,
  },
  steps: {
    type: [StepSchema],
    required: true,
  },
  ingredients: {
    type: [String],
    required: true,
  },
  isPrivate: {
    type: Boolean,
    required: true,
  },
  createdAt: {
    type: Date,
    required: true,
  },
  minutesToCook: {
    type: Number,
    required: true,
  },
  yieldCapacity: {
    type: Number,
    required: true,
  },
  name: {
    type: String,
    required: true,
  },
});
