import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { CreateAccountFacade } from './facades/CreateAccountFacade';
import { ChefClientService } from './http/ChefClientService';
import { HttpClientAdapter } from './http/HttpClientAdapter';
import { AuthService } from './services/AuthService';
import { LocalStorageService } from './services/LocalStorageService';
import { CreateAccountDispatcher } from './store/dispatchers/CreateAccountDispatcher';
import { ClearCreateAccountErrorsHandler } from './store/handlers/ClearCreateAccountErrorsHandler';
import { CreateAccountErrorHandler } from './store/handlers/CreateAccountErrorHandler';

@NgModule({
  imports: [HttpClientModule],
  providers: [
    ChefClientService,
    HttpClientAdapter,
    LocalStorageService,
    CreateAccountDispatcher,
    CreateAccountErrorHandler,
    CreateAccountFacade,
    ClearCreateAccountErrorsHandler,
    AuthService,
  ],
})
export class InfrastructureModule {}
