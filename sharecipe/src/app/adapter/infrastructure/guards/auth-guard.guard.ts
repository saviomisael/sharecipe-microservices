import { inject } from '@angular/core';
import { CanActivateFn } from '@angular/router';
import { AuthService } from '../services/AuthService';

export const authGuardGuard: CanActivateFn = (_, _2) => {
  return inject(AuthService).isLoggedIn();
};
