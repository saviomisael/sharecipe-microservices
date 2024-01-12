import {HttpErrorResponse} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {catchError, Observable, Subscription} from 'rxjs';
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
import {Store} from "@ngrx/store";
import {selectToken} from "../store/selectors/account.selectors";
import {RefreshTokenFacade} from "../facades/RefreshTokenFacade";

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
    private changeUsernameFacade: ChangeUsernameFacade,
    private store: Store,
    private refreshTokenFacade: RefreshTokenFacade
  ) {
  }

  refreshToken(token: string): void {
    const refreshTokenSubscription = this.httpClient.postRefreshToken<TokenResponseDto>('/api/v1/chefs/tokens/refresh-tokens/', token)
      .pipe(
        catchError((error, caught) => {
          refreshTokenSubscription?.unsubscribe();

          this.refreshTokenFacade.clearToken();

          return caught
        })
      )
      .subscribe({
        next: ({data: {token, expiresAt, username}}) => {
          this.refreshTokenFacade.saveRefreshToken({token, expiresAt, username});
          refreshTokenSubscription?.unsubscribe();
        }
      });
  }

  async changeUsername(newUsername: string): Promise<void> {
    this.changeUsernameSubscription = this.httpClient
      .patchWithAuth<ChangeUsernameDto, TokenResponseDto>(
        '/api/v1/chefs/usernames/',
        {newUsername},
        await this.getToken()
      )
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

  async changePassword(password: string): Promise<void> {
    this.changePasswordSubscription = this.httpClient.patchWithAuth<{
      password: string
    }, null>(
      '/api/v1/chefs/passwords/',
      {password},
      await this.getToken()
    )
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

  private getToken(): Promise<string> {
    return new Promise((resolve, reject) => {
      this.store.select(selectToken).subscribe({
        next: resolve,
        error: reject
      })
    })
  }
}
