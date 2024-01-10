import {HttpErrorResponse} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {catchError, Subscription} from 'rxjs';
import {Chef} from '../../../core/models/Chef';
import {CreateAccountFacade} from '../facades/CreateAccountFacade';
import {HttpClientAdapter} from './HttpClientAdapter';
import {IChefClientService} from './contracts/IChefClientService';
import {TokenResponseDto} from './responses/TokenResponseDto';
import {LoginCredentialsDto} from './requests/LoginCredentialsDto';
import {LoginFacade} from "../facades/LoginFacade";

@Injectable()
export class ChefClientService implements IChefClientService {
  private createAccountSubscription: Subscription | null = null;
  private loginSubscription: Subscription | null = null;

  constructor(
    private httpClient: HttpClientAdapter,
    private createAccountFacade: CreateAccountFacade,
    private loginFacade: LoginFacade
  ) {
  }

  login(data: LoginCredentialsDto, loginSuccess: () => void): void {
    this.loginSubscription = this.httpClient.post<LoginCredentialsDto, TokenResponseDto>('/api/v1/chefs/tokens/', data)
      .pipe(
        catchError((error: HttpErrorResponse, caught) => {
          this.unsubscribeLogin()

          const errors = this.getErrorsFromResponse(error)

          this.loginFacade.showErrors(errors)

          return caught
        })
      )
      .subscribe({
        next: ({data: {expiresAt, token, username}}) => {
          this.loginFacade.login({expiresAt, token, username})
          loginSuccess()
        }
      })
  }

  unsubscribeLogin(): void {
    if (this.loginSubscription) {
      this.loginSubscription.unsubscribe()
    }
  }

  createAccount(chef: Chef, createdSuccess: () => void): void {
    this.createAccountSubscription = this.httpClient
      .post<Chef, TokenResponseDto>('/api/v1/chefs/', chef)
      .pipe(
        catchError((error: HttpErrorResponse, caught) => {
          this.unsubscribeCreateAccount();

          const errors = this.getErrorsFromResponse(error);

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

  private getErrorsFromResponse(error: HttpErrorResponse) {
    return error.statusText.includes('Unknown')
      ? ['Service unavailable, try again later']
      : error.error.errors;
  }
}
