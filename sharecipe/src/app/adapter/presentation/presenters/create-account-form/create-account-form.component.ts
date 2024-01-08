import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Chef } from '../../../../core/models/Chef';
import { passwordValidator } from '../../validators/passwordValidator';

interface CreateAccountFormData {
  fullName: string;
  username: string;
  email: string;
  password: string;
  confirmPassword: string;
}

@Component({
  selector: 'app-create-account-form',
  templateUrl: './create-account-form.component.html',
  styleUrl: './create-account-form.component.scss',
})
export class CreateAccountFormComponent implements OnInit {
  form!: FormGroup;
  @Output() onCreateAccount = new EventEmitter<Chef>();
  private isValidConfirmPassword = true;

  constructor(private readonly formBuilder: FormBuilder) {}

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      fullName: [
        '',
        [
          Validators.required,
          Validators.minLength(2),
          Validators.maxLength(255),
        ],
      ],
      username: [
        '',
        [
          Validators.required,
          Validators.minLength(2),
          Validators.maxLength(255),
        ],
      ],
      email: [
        '',
        [Validators.required, Validators.email, Validators.maxLength(255)],
      ],
      password: [
        '',
        [
          Validators.required,
          Validators.minLength(8),
          Validators.maxLength(255),
          passwordValidator,
        ],
      ],
      confirmPassword: ['', Validators.required],
    });
  }

  handleSubmit({ email, fullName, password, username }: CreateAccountFormData) {
    if (this.form.valid) {
      this.onCreateAccount.emit({
        email,
        fullName,
        password,
        username,
      });
    }
  }

  handleConfirmPasswordInput(event: Event) {
    const { value } = event.target as HTMLInputElement;

    this.isValidConfirmPassword = value === this.form.get('password')?.value;
  }

  private isInvalidField(field: string) {
    const input = this.form.get(field);

    if (!input?.dirty) return false;

    const errors = input?.errors;

    return Boolean(errors);
  }

  private passwordsAreDifferent() {
    const password = this.form.get('password');
    const confirmPassword = this.form.get('confirmPassword');

    if (password == null || confirmPassword == null) return true;

    return password.value !== confirmPassword.value;
  }

  get isInvalidFullName() {
    return this.isInvalidField('fullName');
  }

  get isInvalidUsername() {
    return this.isInvalidField('username');
  }

  get isInvalidEmail() {
    return this.isInvalidField('email');
  }

  get isInvalidPassword() {
    return this.isInvalidField('password');
  }

  get isInvalidConfirmPassword() {
    return (
      !this.isValidConfirmPassword || this.isInvalidField('confirmPassword')
    );
  }

  get formIsInvalid() {
    return this.form.invalid || this.passwordsAreDifferent();
  }

  get passwordStyles() {
    return this.isInvalidPassword ? 'input-box -confirmpassword' : 'input-box';
  }
}
