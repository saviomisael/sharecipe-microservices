import {Injectable} from "@angular/core";
import {IHandler} from "./IHandler";
import {Store} from "@ngrx/store";
import {showChangePasswordErrors} from "../actions/account.actions";

@Injectable()
export class ShowChangePasswordErrorsHandler implements IHandler<string[]> {
  constructor(private store: Store) {
  }

  handle(params: string[]): void {
    this.store.dispatch(showChangePasswordErrors({errors: params}))
  }
}
