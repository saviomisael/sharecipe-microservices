import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { CreateAccountFormComponent } from './create-account-form/create-account-form.component';
import { ChefIconComponent } from './icons/chef-icon/chef-icon.component';
import { SharecipeLogoComponent } from './sharecipe-logo/sharecipe-logo.component';

@NgModule({
  declarations: [
    ChefIconComponent,
    CreateAccountFormComponent,
    SharecipeLogoComponent,
  ],
  imports: [
    CommonModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    ReactiveFormsModule,
    MatButtonModule,
  ],
  exports: [ChefIconComponent, CreateAccountFormComponent],
})
export class PresentersModule {}
