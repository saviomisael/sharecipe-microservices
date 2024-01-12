import {Injectable} from "@angular/core";
import {CreateAccountDispatcherParams, SaveAccountInfoDispatcher} from "./SaveAccountInfoDispatcher";
import {Store} from "@ngrx/store";
import {LocalStorageService} from "../../services/LocalStorageService";
import {showChangeUsernameSuccessMessage} from "../actions/account.actions";

@Injectable()
export class ChangeUsernameSuccessDispatcher extends SaveAccountInfoDispatcher {
  constructor(store: Store, localService: LocalStorageService) {
    super(store, localService);
  }

  override dispatch({
                      username,
                      expiresAt,
                      token,
                    }: CreateAccountDispatcherParams) {
    super.dispatch({
      username,
      expiresAt,
      token,
    });

    this.store.dispatch(showChangeUsernameSuccessMessage());
  }
}
