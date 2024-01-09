import {inject} from '@angular/core';
import {CanActivateFn, Router} from '@angular/router';
import {AuthService} from '../services/AuthService';

export const privateRouteGuard: CanActivateFn = async (_, _2) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  const isLoggedIn = await authService.isLoggedIn()

  if (!isLoggedIn) await router.navigate(['/login'])

  return isLoggedIn;
};
