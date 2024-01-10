import {Injectable} from "@angular/core";
import {SaveAccountInfoDispatcher} from "../store/dispatchers/SaveAccountInfoDispatcher";
import {LoginErrorHandler} from "../store/handlers/LoginErrorHandler";
import {TokenResponseDto} from "../http/responses/TokenResponseDto";

@Injectable()
export class LoginFacade {
  constructor(private saveAccountInfoDispatcher: SaveAccountInfoDispatcher, private loginErrorHandler: LoginErrorHandler) {
  }

  login({
          expiresAt,
          username,
          token,
        }: Omit<TokenResponseDto, 'fullName'>) {
    this.saveAccountInfoDispatcher.dispatch({expiresAt, username, token})
  }

  showErrors(errors: string[]) {
    this.loginErrorHandler.handle(errors)
  }
}
