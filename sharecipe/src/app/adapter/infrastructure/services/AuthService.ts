import {isPlatformBrowser} from '@angular/common';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';
import {Store} from '@ngrx/store';
import {setAccountInfo} from '../store/actions/account.actions';
import {selectExpiresAt} from '../store/selectors/account.selectors';
import {LocalStorageService} from './LocalStorageService';

@Injectable()
export class AuthService {
  constructor(
    @Inject(PLATFORM_ID) private platformId: any,
    private store: Store,
    private localService: LocalStorageService
  ) {
  }

  async isLoggedIn(): Promise<boolean> {
    this.loadAccountInfo();

    const expiresAt = await this.getExpiresAt();

    if (!expiresAt) return false;

    const expiresAtDate = new Date(expiresAt);

    if (new Date() > expiresAtDate) return false;

    return true;
  }

  private loadAccountInfo() {
    if (isPlatformBrowser(this.platformId)) {
      let accountInfo = this.localService.getCurrentUser();

      if (
        !accountInfo.expiresAt ||
        !accountInfo.token ||
        !accountInfo.username
      ) {
        accountInfo = {
          token: '',
          username: '',
          expiresAt: '',
        };
      }

      this.store.dispatch(
        setAccountInfo({
          ...accountInfo,
        })
      );
    }
  }

  private getExpiresAt(): Promise<string> {
    return new Promise((resolve, _) => {
      this.store.select(selectExpiresAt).subscribe({
        next: (v) => resolve(v),
      });
    });
  }
}
