import {Injectable} from "@angular/core";
import {IVoidHandler} from "./IVoidHandler";
import {Store} from "@ngrx/store";
import {showChangePasswordSuccessMessage} from "../actions/account.actions";

@Injectable()
export class ShowChangePasswordSuccessMessageHandler implements IVoidHandler {
  constructor(private store: Store) {
  }

  handle(): void {
    this.store.dispatch(showChangePasswordSuccessMessage())
  }
}
