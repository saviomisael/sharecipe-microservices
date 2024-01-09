import {Routes} from '@angular/router';
import {
  CreateAccountContainerComponent
} from '../adapter/presentation/containers/create-account-container/create-account-container.component';
import {HomeContainerComponent} from "../adapter/presentation/containers/home-container/home-container.component";
import {authGuardGuard} from "../adapter/infrastructure/guards/auth-guard.guard";

export const routes: Routes = [
  {
    path: 'create-account',
    component: CreateAccountContainerComponent,
  },
  {
    path: '',
    component: HomeContainerComponent,
    canActivate: [authGuardGuard]
  }
];
