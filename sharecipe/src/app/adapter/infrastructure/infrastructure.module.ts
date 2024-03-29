import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
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
import {ChangeUsernameFacade} from "./facades/ChangeUsernameFacade";
import {ChangeUsernameSuccessDispatcher} from "./store/dispatchers/ChangeUsernameSuccessDispatcher";
import {ClearChangeUsernameErrorAndMessageHandler} from "./store/handlers/ClearChangeUsernameErrorAndMessageHandler";
import {ShowChangeUsernameErrorHandler} from "./store/handlers/ShowChangeUsernameErrorHandler";
import {RefreshTokenFacade} from "./facades/RefreshTokenFacade";
import {RefreshTokenInterceptor} from "./http/interceptors/RefreshTokenInterceptor";

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
    ShowChangePasswordSuccessMessageHandler,
    ChangeUsernameFacade,
    ChangeUsernameSuccessDispatcher,
    ClearChangeUsernameErrorAndMessageHandler,
    ShowChangeUsernameErrorHandler,
    RefreshTokenFacade,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: RefreshTokenInterceptor,
      multi: true
    }
  ],
})
export class InfrastructureModule {
}
