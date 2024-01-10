import {Component} from '@angular/core';
import {LoginCredentialsDto} from "../../../infrastructure/http/requests/LoginCredentialsDto";

@Component({
  selector: 'app-login-container',
  templateUrl: './login-container.component.html',
  styleUrl: './login-container.component.scss'
})
export class LoginContainerComponent {
  handleLoginSubmit(data: LoginCredentialsDto) {
    console.log(data)
  }
}
