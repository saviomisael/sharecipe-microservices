import {Component, EventEmitter, OnDestroy, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {LoginCredentialsDto} from "../../../infrastructure/http/requests/LoginCredentialsDto";
import {isInvalidField} from "../../validators/isInvalidField";
import {passwordValidator} from "../../validators/passwordValidator";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrl: './login-form.component.scss'
})
export class LoginFormComponent implements OnInit, OnDestroy {
  @Output() onLoginSubmit = new EventEmitter<LoginCredentialsDto>();
  @Output() onFormChange = new EventEmitter();
  private formChangesSubscription: Subscription | null = null;
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

    this.formChangesSubscription = this.formGroup.valueChanges.subscribe(() => {
      this.onFormChange.emit();
    })
  }

  ngOnDestroy(): void {
    this.formChangesSubscription?.unsubscribe();
  }

  handleSubmit(data: LoginCredentialsDto) {
    // TODO - Disable button after request to backend
    if (this.form.valid) {
      this.onLoginSubmit.emit(data)
    }
  }
}
