import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {LoginCredentialsDto} from "../../../infrastructure/http/requests/LoginCredentialsDto";
import {isInvalidField} from "../../validators/isInvalidField";
import {passwordValidator} from "../../validators/passwordValidator";

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrl: './login-form.component.scss'
})
export class LoginFormComponent implements OnInit {
  @Output() onLoginSubmit = new EventEmitter<LoginCredentialsDto>()
  private formGroup!: FormGroup;

  constructor(private formBuilder: FormBuilder) {
  }

  get form() {
    return this.formGroup
  }

  get isInvalidUsername() {
    return isInvalidField(this.form, 'username');
  }

  get isInvalidPassword() {
    return isInvalidField(this.form, 'password');
  }

  get submitStyles() {
    return this.isInvalidPassword ? 'login-submit -has-errors' : 'login-submit'
  }

  ngOnInit(): void {
    this.formGroup = this.formBuilder.group({
      username: [
        '',
        [
          Validators.required,
          Validators.minLength(2),
          Validators.maxLength(255),
        ],
      ], password: [
        '',
        [
          Validators.required,
          Validators.minLength(8),
          Validators.maxLength(255),
          passwordValidator,
        ],
      ],
    })
  }

  handleSubmit(data: LoginCredentialsDto) {
    // TODO - Disable button after request to backend
    if (this.form.valid) {
      this.onLoginSubmit.emit(data)
    }
  }
}
