import {HttpErrorResponse} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {catchError, Subscription} from 'rxjs';
import {Chef} from '../../../core/models/Chef';
import {CreateAccountFacade} from '../facades/CreateAccountFacade';
import {HttpClientAdapter} from './HttpClientAdapter';
import {IChefClientService} from './contracts/IChefClientService';
import {TokenResponseDto} from './responses/TokenResponseDto';

@Injectable()
export class ChefClientService implements IChefClientService {
  private createAccountSubscription: Subscription | null = null;

  constructor(
    private httpClient: HttpClientAdapter,
    private createAccountFacade: CreateAccountFacade
  ) {
  }

  createAccount(chef: Chef, createdSuccess: () => void): void {
    this.createAccountSubscription = this.httpClient
      .post<Chef, TokenResponseDto>('/api/v1/chefs/', chef)
      .pipe(
        catchError((error: HttpErrorResponse, caught) => {
          this.unsubscribeCreateAccount();

          const errors = error.statusText.includes('Unknown')
            ? ['Service unavailable, try again later']
            : error.error.errors;

          this.createAccountFacade.showErrors(errors);

          return caught;
        })
      )
      .subscribe({
        next: ({data: {expiresAt, token, username}}) => {
          this.createAccountFacade.createAccount({
            expiresAt,
            token,
            username,
          });
          createdSuccess();
        },
      });
  }

  unsubscribeCreateAccount() {
    if (this.createAccountSubscription) {
      this.createAccountSubscription.unsubscribe();
    }
  }
}
