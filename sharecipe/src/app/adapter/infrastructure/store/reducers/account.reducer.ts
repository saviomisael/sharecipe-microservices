import {createReducer, on} from '@ngrx/store';
import {
  clearChangePasswordErrorsAndMessage,
  clearChangeUsernameErrorAndMessage,
  clearCreateAccountErrors,
  clearLoginErrors,
  logout,
  setAccountInfo,
  showChangePasswordErrors,
  showChangePasswordSuccessMessage,
  showChangeUsernameError,
  showChangeUsernameSuccessMessage,
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
  showChangePasswordSuccessMessage: boolean;
  showChangeUsernameError: boolean;
  showChangeUsernameSuccessMessage: boolean;
}

const initialState: AccountState = {
  token: '',
  username: '',
  expiresAt: '',
  createAccountErrors: [],
  loginErrors: [],
  changePasswordErrors: [],
  showChangePasswordSuccessMessage: false,
  showChangeUsernameError: false,
  showChangeUsernameSuccessMessage: false
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
    showChangePasswordSuccessMessage: false
  })),
  on(showChangePasswordSuccessMessage, (state: AccountState): AccountState => ({
    ...state,
    showChangePasswordSuccessMessage: true,
    changePasswordErrors: []
  })),
  on(clearChangePasswordErrorsAndMessage, (state: AccountState): AccountState => ({
    ...state,
    showChangePasswordSuccessMessage: false,
    changePasswordErrors: []
  })),
  on(showChangeUsernameError, (state: AccountState): AccountState => ({
    ...state,
    showChangeUsernameError: true,
    showChangeUsernameSuccessMessage: false
  })),
  on(showChangeUsernameSuccessMessage, (state: AccountState): AccountState => ({
    ...state,
    showChangeUsernameSuccessMessage: true,
    showChangeUsernameError: false
  })),
  on(clearChangeUsernameErrorAndMessage, (state: AccountState): AccountState => ({
    ...state,
    showChangeUsernameError: false,
    showChangeUsernameSuccessMessage: false
  }))
);
