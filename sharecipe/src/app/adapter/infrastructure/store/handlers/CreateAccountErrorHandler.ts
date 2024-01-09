import {Injectable} from '@angular/core';
import {Store} from '@ngrx/store';
import {showCreateAccountErrors} from '../actions/account.actions';
import {IHandler} from './IHandler';

@Injectable()
export class CreateAccountErrorHandler implements IHandler<string[]> {
  constructor(private store: Store) {
  }

  handle(params: string[]): void {
    this.store.dispatch(showCreateAccountErrors({errors: params}));
  }
}
