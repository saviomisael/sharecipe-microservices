import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { ChefClientService } from './http/ChefClientService';
import { HttpClientAdapter } from './http/HttpClientAdapter';
import { LocalStorageService } from './services/LocalStorageService';
import { CreateAccountDispatcher } from './store/dispatchers/CreateAccountDispatcher';
import { CreateAccountErrorHandler } from './store/handlers/CreateAccountErrorHandler';
import { CreateAccountFacade } from './facades/CreateAccountFacade';

@NgModule({
  imports: [HttpClientModule],
  providers: [
    ChefClientService,
    HttpClientAdapter,
    LocalStorageService,
    CreateAccountDispatcher,
    CreateAccountErrorHandler,
    CreateAccountFacade,
  ],
})
export class InfrastructureModule {}
