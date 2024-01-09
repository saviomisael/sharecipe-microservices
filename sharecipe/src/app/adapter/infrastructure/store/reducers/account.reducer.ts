import {createReducer, on} from '@ngrx/store';
import {clearCreateAccountErrors, logout, setAccountInfo, showCreateAccountErrors,} from '../actions/account.actions';

export interface AccountState {
  token: string;
  username: string;
  expiresAt: string;
  createAccountErrors: string[];
}

const initialState: AccountState = {
  token: '',
  username: '',
  expiresAt: '',
  createAccountErrors: [],
};

export const accountReducer = createReducer(
  initialState,
  on(
    setAccountInfo,
    (state, {expiresAt, token, username}): AccountState => ({
      ...state,
      expiresAt,
      token,
      username,
    })
  ),
  on(
    showCreateAccountErrors,
    (state, {errors}): AccountState => ({
      ...state,
      createAccountErrors: errors,
    })
  ),
  on(
    clearCreateAccountErrors,
    (state): AccountState => ({
      ...state,
      createAccountErrors: [],
    })
  ),
  on(logout, (state: AccountState): AccountState => ({
    ...state,
    expiresAt: '',
    token: '',
    username: ''
  }))
);
