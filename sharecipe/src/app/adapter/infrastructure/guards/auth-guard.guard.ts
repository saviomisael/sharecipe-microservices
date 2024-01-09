import { inject } from '@angular/core';
import { CanActivateFn } from '@angular/router';
import { AuthService } from '../services/AuthService';

export const authGuardGuard: CanActivateFn = async (_, _2) => {
  return await inject(AuthService).isLoggedIn();
};
