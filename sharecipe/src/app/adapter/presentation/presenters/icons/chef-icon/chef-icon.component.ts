import { Component } from '@angular/core';
import chefIcon from './chef.png';

@Component({
  selector: 'app-chef-icon',
  templateUrl: './chef-icon.component.html',
  styleUrl: './chef-icon.component.scss',
})
export class ChefIconComponent {
  public getChefIcon() {
    return chefIcon;
  }
}
