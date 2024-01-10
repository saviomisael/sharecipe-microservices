import {Injectable} from '@angular/core';
import {TokenResponseDto} from '../http/responses/TokenResponseDto';
import {CreateAccountDispatcher} from '../store/dispatchers/CreateAccountDispatcher';
import {CreateAccountErrorHandler} from '../store/handlers/CreateAccountErrorHandler';

@Injectable()
export class CreateAccountFacade {
  constructor(
    private createAccountDispatcher: CreateAccountDispatcher,
    private createAccountErrorHandler: CreateAccountErrorHandler
  ) {
  }

  createAccount({
                  expiresAt,
                  username,
                  token,
                }: Omit<TokenResponseDto, 'fullName'>) {
    this.createAccountDispatcher.dispatch({expiresAt, token, username});
  }

  showErrors(errors: string[]) {
    this.createAccountErrorHandler.handle(errors);
  }
}
