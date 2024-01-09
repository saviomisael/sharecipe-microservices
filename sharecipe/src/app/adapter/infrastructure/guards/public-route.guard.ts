import {CanActivateFn, Router} from '@angular/router';
import {inject} from "@angular/core";
import {AuthService} from "../services/AuthService";

export const publicRouteGuard: CanActivateFn = async (_, _2) => {
  const authService = inject(AuthService);
  const router = inject(Router);
  const isLoggedIn = await authService.isLoggedIn();

  if (isLoggedIn) await router.navigate(['/'])

  return !isLoggedIn;
};
