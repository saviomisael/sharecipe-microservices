import { Component, OnDestroy } from '@angular/core';
import { Chef } from '../../../../core/models/Chef';
import { ChefClientService } from '../../../infrastructure/http/ChefClientService';

@Component({
  selector: 'app-create-account-container',
  templateUrl: './create-account-container.component.html',
  styleUrl: './create-account-container.component.scss',
})
export class CreateAccountContainerComponent implements OnDestroy {
  constructor(private chefClientService: ChefClientService) {}
  ngOnDestroy(): void {
    this.chefClientService.unsubscribeCreateAccount();
  }

  handleCreateAccountSubmit(data: Chef) {
    this.chefClientService.createAccount(data);
  }
}
