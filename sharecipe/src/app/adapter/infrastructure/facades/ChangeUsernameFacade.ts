import {Injectable} from "@angular/core";
import {ChangeUsernameSuccessDispatcher} from "../store/dispatchers/ChangeUsernameSuccessDispatcher";
import {ShowChangeUsernameErrorHandler} from "../store/handlers/ShowChangeUsernameErrorHandler";

@Injectable()
export class ChangeUsernameFacade {
  constructor(private changeUsernameSuccessDispatcher: ChangeUsernameSuccessDispatcher, private showChangeUsernameErrorHandler: ShowChangeUsernameErrorHandler) {
  }

  showSuccessMessage(username: string, expiresAt: string, token: string) {
    this.changeUsernameSuccessDispatcher.dispatch({username, expiresAt, token});
  }

  showErrors() {
    this.showChangeUsernameErrorHandler.handle();
  }
}
