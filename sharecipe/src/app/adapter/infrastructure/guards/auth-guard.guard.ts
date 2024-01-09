import {inject} from '@angular/core';
import {CanActivateFn} from '@angular/router';
import {AuthService} from '../services/AuthService';

export const authGuardGuard: CanActivateFn = async (_, _2) => {
  const authService = inject(AuthService);

  return await authService.isLoggedIn();
};
