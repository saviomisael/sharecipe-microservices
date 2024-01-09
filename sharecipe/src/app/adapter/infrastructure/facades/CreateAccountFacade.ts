import {Injectable} from '@angular/core';
import {CreateAccountResponseDto} from '../http/responses/CreateAccountResponseDto';
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
                }: Omit<CreateAccountResponseDto, 'fullName'>) {
    this.createAccountDispatcher.dispatch({expiresAt, token, username});
  }

  showErrors(errors: string[]) {
    this.createAccountErrorHandler.handle(errors);
  }
}
