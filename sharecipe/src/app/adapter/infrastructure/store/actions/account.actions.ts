import { createAction, props } from '@ngrx/store';

export const setAccountInfo = createAction(
  '[ACCOUNT] Add info',
  props<{
    username: string;
    token: string;
    expiresAt: string;
  }>()
);

export const showCreateAccountErrors = createAction(
  '[ACCOUNT] Show errors',
  props<{ errors: string[] }>()
);
