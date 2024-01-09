import {Routes} from '@angular/router';
import {
  CreateAccountContainerComponent
} from '../adapter/presentation/containers/create-account-container/create-account-container.component';
import {HomeContainerComponent} from "../adapter/presentation/containers/home-container/home-container.component";
import {privateRouteGuard} from "../adapter/infrastructure/guards/private-route.guard";
import {LoginContainerComponent} from "../adapter/presentation/containers/login-container/login-container.component";
import {publicRouteGuard} from "../adapter/infrastructure/guards/public-route.guard";

export const routes: Routes = [
  {
    path: 'create-account',
    component: CreateAccountContainerComponent,
    canActivate: [publicRouteGuard]
  },
  {
    path: '',
    component: HomeContainerComponent,
    canActivate: [privateRouteGuard]
  },
  {
    path: 'login',
    component: LoginContainerComponent,
    canActivate: [publicRouteGuard]
  }
];
