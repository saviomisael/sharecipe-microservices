import {Injectable} from "@angular/core";
import {IVoidHandler} from "./IVoidHandler";
import {Store} from "@ngrx/store";
import {clearChangeUsernameErrorAndMessage} from "../actions/account.actions";

@Injectable()
export class ClearChangeUsernameErrorAndMessageHandler implements IVoidHandler {
  constructor(private store: Store) {
  }

  handle(): void {
    this.store.dispatch(clearChangeUsernameErrorAndMessage());
  }
}
