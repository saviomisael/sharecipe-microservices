import { createReducer, on } from '@ngrx/store';
import { setAccountInfo } from '../actions/account.actions';

interface InitialState {
  token: string;
  username: string;
  expiresAt: string;
}

const initialState: InitialState = {
  token: '',
  username: '',
  expiresAt: '',
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
  )
);
