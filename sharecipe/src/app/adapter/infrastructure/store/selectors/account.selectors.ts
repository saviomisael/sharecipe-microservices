import { createFeatureSelector, createSelector } from '@ngrx/store';
import { AccountState } from '../reducers/account.reducer';

const selectAccountState = createFeatureSelector<AccountState>('account');

export const selectCreateAccountErrors = createSelector(
  selectAccountState,
  (state: AccountState) => state.createAccountErrors
);
