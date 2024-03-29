import {ApplicationConfig, isDevMode} from '@angular/core';
import {provideRouter} from '@angular/router';

import {provideHttpClient, withFetch} from '@angular/common/http';
import {provideClientHydration} from '@angular/platform-browser';
import {provideAnimations} from '@angular/platform-browser/animations';
import {provideStore} from '@ngrx/store';
import {rootReducer} from '../adapter/infrastructure/store/reducers/rootReducer.reducer';
import {routes} from './app.routes';
import {provideStoreDevtools} from '@ngrx/store-devtools';
import {AuthService} from "../adapter/infrastructure/services/AuthService";
import {LocalStorageService} from "../adapter/infrastructure/services/LocalStorageService";
import {
  ClearChangePasswordErrorsAndMessageHandler
} from "../adapter/infrastructure/store/handlers/ClearChangePasswordErrorsAndMessageHandler";

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),
    provideClientHydration(),
    provideAnimations(),
    provideHttpClient(withFetch()),
    provideStore(rootReducer),
    provideStoreDevtools({maxAge: 25, logOnly: !isDevMode()}),
    AuthService,
    LocalStorageService,
    ClearChangePasswordErrorsAndMessageHandler
  ],
};
