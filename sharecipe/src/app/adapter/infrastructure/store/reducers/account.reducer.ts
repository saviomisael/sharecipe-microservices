import {createReducer, on} from '@ngrx/store';
import {
  clearCreateAccountErrors,
  clearLoginErrors,
  logout,
  setAccountInfo,
  showChangePasswordErrors,
  showChangePasswordSuccessMessage,
  showCreateAccountErrors,
  showLoginErrors,
} from '../actions/account.actions';

export interface AccountState {
  token: string;
  username: string;
  expiresAt: string;
  createAccountErrors: string[];
  loginErrors: string[];
  changePasswordErrors: string[];
  changePasswordSuccessMessage: boolean;
}

const initialState: AccountState = {
  token: '',
  username: '',
  expiresAt: '',
  createAccountErrors: [],
  loginErrors: [],
  changePasswordErrors: [],
  changePasswordSuccessMessage: false
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
  })),
  on(showLoginErrors, (state: AccountState, {errors}): AccountState => ({
    ...state,
    loginErrors: errors
  })),
  on(clearLoginErrors, (state: AccountState): AccountState => ({
    ...state,
    loginErrors: []
  })),
  on(showChangePasswordErrors, (state: AccountState, {errors}): AccountState => ({
    ...state,
    changePasswordErrors: errors,
    changePasswordSuccessMessage: false
  })),
  on(showChangePasswordSuccessMessage, (state: AccountState): AccountState => ({
    ...state,
    changePasswordSuccessMessage: true,
    changePasswordErrors: []
  }))
);
