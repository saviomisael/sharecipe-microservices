import {Injectable} from "@angular/core";
import {IVoidHandler} from "./IVoidHandler";
import {Store} from "@ngrx/store";
import {showChangeUsernameError} from "../actions/account.actions";

@Injectable()
export class ShowChangeUsernameErrorHandler implements IVoidHandler {
  constructor(private store: Store) {
  }

  handle(): void {
    this.store.dispatch(showChangeUsernameError());
  }
}
