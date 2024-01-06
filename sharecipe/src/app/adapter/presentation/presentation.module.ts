import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { ChefIconComponent } from './presenters/icons/chef-icon/chef-icon.component';

@NgModule({
  declarations: [ChefIconComponent],
  imports: [CommonModule, MatFormFieldModule, MatInputModule, MatIconModule],
  exports: [],
})
export class PresentationModule {}
