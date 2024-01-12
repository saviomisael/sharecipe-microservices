import {Injectable} from "@angular/core";
import {TokenResponseDto} from "../http/responses/TokenResponseDto";
import {SaveAccountInfoDispatcher} from "../store/dispatchers/SaveAccountInfoDispatcher";
import {LogoutDispatcher} from "../store/dispatchers/LogoutDispatcher";

@Injectable()
export class RefreshTokenFacade {
  constructor(private saveAccountInfoDispatcher: SaveAccountInfoDispatcher, private logoutDispatcher: LogoutDispatcher) {
  }

  saveRefreshToken(tokenData: Omit<TokenResponseDto, "fullName">) {
    this.saveAccountInfoDispatcher.dispatch(tokenData);
  }

  clearToken() {
    this.logoutDispatcher.dispatch();
  }
}
