import { HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Subscription, catchError } from 'rxjs';
import { Chef } from '../../../core/models/Chef';
import { CreateAccountFacade } from '../facades/CreateAccountFacade';
import { HttpClientAdapter } from './HttpClientAdapter';
import { IChefClientService } from './contracts/IChefClientService';
import { CreateAccountResponseDto } from './responses/CreateAccountResponseDto';

@Injectable()
export class ChefClientService implements IChefClientService {
  private createAccountSubscription: Subscription | null = null;

  constructor(
    private httpClient: HttpClientAdapter,
    private createAccountFacade: CreateAccountFacade
  ) {}

  createAccount(chef: Chef): void {
    this.createAccountSubscription = this.httpClient
      .post<Chef, CreateAccountResponseDto>('/api/v1/chefs/', chef)
      .pipe(
        catchError((error: HttpErrorResponse, caught) => {
          this.unsubscribeCreateAccount();

          this.createAccountFacade.showErrors(error.error.errors);

          return caught;
        })
      )
      .subscribe({
        next: ({ data: { expiresAt, token, username } }) => {
          this.createAccountFacade.createAccount({
            expiresAt,
            token,
            username,
          });
        },
      });
  }

  unsubscribeCreateAccount() {
    if (this.createAccountSubscription) {
      this.createAccountSubscription.unsubscribe();
    }
  }
}
