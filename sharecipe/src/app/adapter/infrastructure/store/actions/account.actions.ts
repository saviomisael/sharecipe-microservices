import {createAction, props} from '@ngrx/store';

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

export const logout = createAction('[ACCOUNT] logout')

export const showLoginErrors = createAction(
  '[LOGIN] Show login errors',
  props<{ errors: string[] }>()
)

export const clearLoginErrors = createAction('[LOGIN] clear login errors')

export const showChangePasswordErrors = createAction(
  '[CHANGE PASSWORD] Show errors',
  props<{ errors: string[] }>()
)

export const showChangePasswordSuccessMessage = createAction(
  '[CHANGE PASSWORD] Show success message'
)

export const clearChangePasswordErrorsAndMessage = createAction('[CHANGE PASSWORD] Clear errors and message')
