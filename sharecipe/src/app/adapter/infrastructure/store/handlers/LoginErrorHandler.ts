import {Injectable} from "@angular/core";
import {IHandler} from "./IHandler";
import {Store} from "@ngrx/store";
import {showLoginErrors} from "../actions/account.actions";

@Injectable()
export class LoginErrorHandler implements IHandler<string[]> {
  constructor(private store: Store) {
  }

  handle(params: string[]): void {
    this.store.dispatch(showLoginErrors({errors: params}))
  }
}
