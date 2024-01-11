import {Injectable} from "@angular/core";
import {ShowChangePasswordErrorsHandler} from "../store/handlers/ShowChangePasswordErrorsHandler";
import {ShowChangePasswordSuccessMessageHandler} from "../store/handlers/ShowChangePasswordSuccessMessageHandler";

@Injectable()
export class ChangePasswordFacade {
  constructor(private showChangePasswordErrorsHandler: ShowChangePasswordErrorsHandler,
              private showChangePasswordSuccessMessageHandler: ShowChangePasswordSuccessMessageHandler) {
  }

  showErrors(errors: string[]) {
    this.showChangePasswordErrorsHandler.handle(errors);
  }

  showSuccessMessage() {
    this.showChangePasswordSuccessMessageHandler.handle()
  }
}
