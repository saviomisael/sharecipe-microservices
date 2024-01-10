import {Injectable} from '@angular/core';
import {TokenResponseDto} from '../http/responses/TokenResponseDto';
import {SaveAccountInfoDispatcher} from '../store/dispatchers/SaveAccountInfoDispatcher';
import {CreateAccountErrorHandler} from '../store/handlers/CreateAccountErrorHandler';

@Injectable()
export class CreateAccountFacade {
  constructor(
    private createAccountDispatcher: SaveAccountInfoDispatcher,
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
