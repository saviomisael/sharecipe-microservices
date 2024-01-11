import {HttpClientModule} from '@angular/common/http';
import {NgModule} from '@angular/core';
import {CreateAccountFacade} from './facades/CreateAccountFacade';
import {ChefClientService} from './http/ChefClientService';
import {HttpClientAdapter} from './http/HttpClientAdapter';
import {AuthService} from './services/AuthService';
import {LocalStorageService} from './services/LocalStorageService';
import {SaveAccountInfoDispatcher} from './store/dispatchers/SaveAccountInfoDispatcher';
import {ClearCreateAccountErrorsHandler} from './store/handlers/ClearCreateAccountErrorsHandler';
import {CreateAccountErrorHandler} from './store/handlers/CreateAccountErrorHandler';
import {LogoutDispatcher} from "./store/dispatchers/LogoutDispatcher";
import {LoginErrorHandler} from "./store/handlers/LoginErrorHandler";
import {ClearLoginErrorHandler} from "./store/handlers/ClearLoginErrorHandler";
import {LoginFacade} from "./facades/LoginFacade";
import {ChangePasswordFacade} from "./facades/ChangePasswordFacade";
import {ShowChangePasswordErrorsHandler} from "./store/handlers/ShowChangePasswordErrorsHandler";
import {ShowChangePasswordSuccessMessageHandler} from "./store/handlers/ShowChangePasswordSuccessMessageHandler";

@NgModule({
  imports: [HttpClientModule],
  providers: [
    ChefClientService,
    HttpClientAdapter,
    LocalStorageService,
    SaveAccountInfoDispatcher,
    CreateAccountErrorHandler,
    CreateAccountFacade,
    ClearCreateAccountErrorsHandler,
    AuthService,
    LogoutDispatcher,
    LoginErrorHandler,
    ClearLoginErrorHandler,
    LoginFacade,
    ChangePasswordFacade,
    ShowChangePasswordErrorsHandler,
    ShowChangePasswordSuccessMessageHandler
  ],
})
export class InfrastructureModule {
}
