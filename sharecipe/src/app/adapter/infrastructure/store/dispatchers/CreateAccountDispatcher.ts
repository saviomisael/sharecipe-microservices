import {Injectable} from '@angular/core';
import {Store} from '@ngrx/store';
import {LocalStorageService} from '../../services/LocalStorageService';
import {setAccountInfo} from '../actions/account.actions';
import {IDispatcher} from './IDispatcher';

export interface CreateAccountDispatcherParams {
  username: string;
  token: string;
  expiresAt: string;
}

@Injectable()
export class CreateAccountDispatcher
  implements IDispatcher<CreateAccountDispatcherParams> {
  constructor(
    private store: Store,
    private localService: LocalStorageService
  ) {
  }

  dispatch({
             username,
             expiresAt,
             token,
           }: CreateAccountDispatcherParams): void {
    this.localService.saveCurrentUser(token, expiresAt, username);
    this.store.dispatch(setAccountInfo({username, expiresAt, token}));
  }
}
