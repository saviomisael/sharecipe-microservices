import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {ReactiveFormsModule} from '@angular/forms';
import {MatButtonModule} from '@angular/material/button';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatIconModule} from '@angular/material/icon';
import {MatInputModule} from '@angular/material/input';
import {CreateAccountFormComponent} from './create-account-form/create-account-form.component';
import {ErrorsListComponent} from './errors-list/errors-list.component';
import {ChefIconComponent} from './icons/chef-icon/chef-icon.component';
import {MainHeaderComponent} from './main-header/main-header.component';
import {SharecipeLogoComponent} from './sharecipe-logo/sharecipe-logo.component';
import {LoginFormComponent} from "./login-form/login-form.component";
import {RouterModule} from "@angular/router";

@NgModule({
  declarations: [
    ChefIconComponent,
    CreateAccountFormComponent,
    SharecipeLogoComponent,
    ErrorsListComponent,
    MainHeaderComponent,
    LoginFormComponent
  ],
  imports: [
    CommonModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    ReactiveFormsModule,
    MatButtonModule,
    RouterModule
  ],
  exports: [
    ChefIconComponent,
    CreateAccountFormComponent,
    SharecipeLogoComponent,
    ErrorsListComponent,
    MainHeaderComponent,
    LoginFormComponent
  ],
})
export class PresentersModule {
}
