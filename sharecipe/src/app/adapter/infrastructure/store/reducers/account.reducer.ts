import { createReducer, on } from '@ngrx/store';
import {
  setAccountInfo,
  showCreateAccountErrors,
} from '../actions/account.actions';

interface InitialState {
  token: string;
  username: string;
  expiresAt: string;
  createAccountErrors: string[];
}

const initialState: InitialState = {
  token: '',
  username: '',
  expiresAt: '',
  createAccountErrors: [],
};

export const accountReducer = createReducer(
  initialState,
  on(
    setAccountInfo,
    (state, { expiresAt, token, username }): InitialState => ({
      ...state,
      expiresAt,
      token,
      username,
    })
  ),
  on(
    showCreateAccountErrors,
    (state, { errors }): InitialState => ({
      ...state,
      createAccountErrors: errors,
    })
  )
);
