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
import {ChangePasswordFacade} from "../facades/ChangePasswordFacade";
import {ChangeUsernameFacade} from "../facades/ChangeUsernameFacade";
import {ChangeUsernameDto} from "./requests/ChangeUsernameDto";

@Injectable()
export class ChefClientService implements IChefClientService {
  private createAccountSubscription: Subscription | null = null;
  private loginSubscription: Subscription | null = null;
  private changePasswordSubscription: Subscription | null = null;
  private changeUsernameSubscription: Subscription | null = null;

  constructor(
    private httpClient: HttpClientAdapter,
    private createAccountFacade: CreateAccountFacade,
    private loginFacade: LoginFacade,
    private changePasswordFacade: ChangePasswordFacade,
    private changeUsernameFacade: ChangeUsernameFacade
  ) {
  }

  changeUsername(newUsername: string): void {
    this.changeUsernameSubscription = this.httpClient.patch<ChangeUsernameDto, TokenResponseDto>('/api/chefs/usernames', {newUsername})
      .pipe(
        catchError((error: HttpErrorResponse, caught) => {
          this.unsubscribeChangeUsername();

          this.changeUsernameFacade.showErrors()

          return caught
        })
      )
      .subscribe({
        next: ({data}) => {
          this.changeUsernameFacade.showSuccessMessage(data.username, data.expiresAt, data.token);
        }
      })
  }

  unsubscribeChangeUsername(): void {
    this.changeUsernameSubscription?.unsubscribe();
  }

  changePassword(password: string): void {
    this.changePasswordSubscription = this.httpClient.patch<{ password: string }, null>('/api/chefs/passwords',
      {password})
      .pipe(
        catchError((error: HttpErrorResponse, caught) => {
          this.unsubscribeChangePassword();

          this.changePasswordFacade.showErrors(
            error.statusText.includes('Unknown')
              ? ['Service unavailable, try again later']
              : ['Your not authorized to change your password']
          );

          return caught
        })
      ).subscribe({
        next: () => {
          this.changePasswordFacade.showSuccessMessage()
        }
      })

  }

  unsubscribeChangePassword(): void {
    this.changePasswordSubscription?.unsubscribe();
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
    this.loginSubscription?.unsubscribe()
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
    this.createAccountSubscription?.unsubscribe();
  }

  private getErrorsFromResponse(error: HttpErrorResponse) {
    return error.statusText.includes('Unknown')
      ? ['Service unavailable, try again later']
      : error.error.errors;
  }
}
