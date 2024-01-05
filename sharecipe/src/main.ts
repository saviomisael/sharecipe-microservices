import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/main/app.component';
import { appConfig } from './app/main/app.config';

bootstrapApplication(AppComponent, appConfig).catch((err) =>
  console.error(err)
);
