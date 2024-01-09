import { Injectable } from '@angular/core';
import { Store } from '@ngrx/store';
import { setAccountInfo } from '../store/actions/account.actions';
import { LocalStorageService } from './LocalStorageService';

@Injectable()
export class AuthService {
  constructor(
    private store: Store,
    private localService: LocalStorageService
  ) {}

  isLoggedIn(): boolean {
    const accountInfo = this.localService.getCurrentUser();

    if (!accountInfo.expiresAt) return false;

    const expiresAtDate = new Date(accountInfo.expiresAt);

    if (new Date() > expiresAtDate) return false;

    this.store.dispatch(
      setAccountInfo({
        ...accountInfo,
      })
    );

    return true;
  }
}
