import { createAction, props } from '@ngrx/store';

export const setAccountInfo = createAction(
  '[ACCOUNT] Add account info',
  props<{
    username: string;
    token: string;
    expiresAt: string;
  }>()
);

export const showCreateAccountErrors = createAction(
  '[ACCOUNT] Show create account errors',
  props<{ errors: string[] }>()
);

export const clearCreateAccountErrors = createAction(
  '[ACCOUNT] Clear create account errors'
);
