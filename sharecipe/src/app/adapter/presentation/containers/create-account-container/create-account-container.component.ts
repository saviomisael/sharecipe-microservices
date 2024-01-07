import { Component } from '@angular/core';
import { Chef } from '../../../../core/models/Chef';

@Component({
  selector: 'app-create-account-container',
  templateUrl: './create-account-container.component.html',
  styleUrl: './create-account-container.component.scss',
})
export class CreateAccountContainerComponent {
  handleCreateAccountSubmit(data: Chef) {
    // TODO - integrate with backend

    console.log(data);
  }
}
