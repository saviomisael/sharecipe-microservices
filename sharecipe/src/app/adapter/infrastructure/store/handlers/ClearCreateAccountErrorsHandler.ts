import {Injectable} from '@angular/core';
import {Store} from '@ngrx/store';
import {clearCreateAccountErrors} from '../actions/account.actions';
import {IVoidHandler} from './IVoidHandler';

@Injectable()
export class ClearCreateAccountErrorsHandler implements IVoidHandler {
  constructor(private store: Store) {
  }

  handle(): void {
    this.store.dispatch(clearCreateAccountErrors());
  }
}
