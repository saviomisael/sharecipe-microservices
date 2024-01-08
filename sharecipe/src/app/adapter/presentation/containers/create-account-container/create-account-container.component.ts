import { Component } from '@angular/core';
import { Chef } from '../../../../core/models/Chef';
import { ChefClientService } from '../../../infrastructure/http/ChefClientService';

@Component({
  selector: 'app-create-account-container',
  templateUrl: './create-account-container.component.html',
  styleUrl: './create-account-container.component.scss',
})
export class CreateAccountContainerComponent {
  constructor(private chefClientService: ChefClientService) {}

  handleCreateAccountSubmit(data: Chef) {
    this.chefClientService.createAccount(data);
  }
}
