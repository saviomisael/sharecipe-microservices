import {Injectable} from "@angular/core";
import {IVoidHandler} from "./IVoidHandler";
import {Store} from "@ngrx/store";
import {clearLoginErrors} from "../actions/account.actions";

@Injectable()
export class ClearLoginErrorHandler implements IVoidHandler {
  constructor(private store: Store) {
  }

  handle(): void {
    this.store.dispatch(clearLoginErrors())
  }
}
