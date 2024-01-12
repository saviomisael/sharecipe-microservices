import {createFeatureSelector, createSelector} from '@ngrx/store';
import {AccountState} from '../reducers/account.reducer';

const selectAccountState = createFeatureSelector<AccountState>('account');

export const selectCreateAccountErrors = createSelector(
  selectAccountState,
  (state: AccountState) => state.createAccountErrors
);

export const selectExpiresAt = createSelector(
  selectAccountState,
  (state: AccountState) => state.expiresAt
);

export const selectUsername = createSelector(
  selectAccountState,
  ({username}: AccountState) => username,
);

export const selectLoginErrors = createSelector(
  selectAccountState,
  ({loginErrors}: AccountState) => loginErrors
);

export const selectChangePasswordErrors = createSelector(
  selectAccountState,
  ({changePasswordErrors}: AccountState) => changePasswordErrors[0] ?? ''
);

export const selectShowChangePasswordSuccessMessage = createSelector(
  selectAccountState,
  ({showChangePasswordSuccessMessage}: AccountState) => showChangePasswordSuccessMessage
);

export const selectShowChangeUsernameError = createSelector(
  selectAccountState,
  ({showChangeUsernameError}: AccountState) => showChangeUsernameError
);

export const selectShowChangeUsernameSuccessMessage = createSelector(
  selectAccountState,
  ({showChangeUsernameSuccessMessage}: AccountState) => showChangeUsernameSuccessMessage
);
