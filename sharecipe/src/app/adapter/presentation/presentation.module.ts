import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { CreateAccountFormComponent } from './presenters/create-account-form/create-account-form.component';

@NgModule({
  declarations: [CreateAccountFormComponent],
  imports: [CommonModule],
  exports: [CreateAccountFormComponent],
})
export class PresentationModule {}
