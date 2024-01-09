import {Inject, Injectable, PLATFORM_ID} from "@angular/core";
import {IVoidDispatcher} from "./IVoidDispatcher";
import {Store} from "@ngrx/store";
import {LocalStorageService} from "../../services/LocalStorageService";
import {isPlatformBrowser} from "@angular/common";
import {logout} from "../actions/account.actions";

@Injectable()
export class LogoutDispatcher implements IVoidDispatcher {
  constructor(private store: Store, private localStorage: LocalStorageService, @Inject(PLATFORM_ID) private platformId: any) {
  }

  dispatch(): void {
    if (isPlatformBrowser(this.platformId)) {
      this.localStorage.logout()
    }

    this.store.dispatch(logout())
  }
}
