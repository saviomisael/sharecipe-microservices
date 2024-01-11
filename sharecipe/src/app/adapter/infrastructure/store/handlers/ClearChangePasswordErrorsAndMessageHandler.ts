import {Injectable} from "@angular/core";
import {IVoidHandler} from "./IVoidHandler";
import {Store} from "@ngrx/store";
import {clearChangePasswordErrorsAndMessage} from "../actions/account.actions";

@Injectable()
export class ClearChangePasswordErrorsAndMessageHandler implements IVoidHandler {
  constructor(private store: Store) {
  }

  handle(): void {
    this.store.dispatch(clearChangePasswordErrorsAndMessage())
  }
}
