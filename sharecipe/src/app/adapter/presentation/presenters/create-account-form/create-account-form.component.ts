import {Component, EventEmitter, OnDestroy, OnInit, Output,} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Subscription} from 'rxjs';
import {Chef} from '../../../../core/models/Chef';
import {passwordValidator} from '../../validators/passwordValidator';
import {isInvalidField} from "../../validators/isInvalidField";

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
export class CreateAccountFormComponent implements OnInit, OnDestroy {
  @Output() onCreateAccount = new EventEmitter<Chef>();
  @Output() onFormChange = new EventEmitter();

  form!: FormGroup;
  private isValidConfirmPassword = true;
  private formChangesSubscription: Subscription | null = null;

  constructor(private readonly formBuilder: FormBuilder) {
  }

  get isInvalidFullName() {
    return isInvalidField(this.form, 'fullName');
  }

  get isInvalidUsername() {
    return isInvalidField(this.form, 'username');
  }

  get isInvalidEmail() {
    return isInvalidField(this.form, 'email');
  }

  get isInvalidPassword() {
    return isInvalidField(this.form, 'password');
  }

  get isInvalidConfirmPassword() {
    return (
      !this.isValidConfirmPassword || isInvalidField(this.form, 'confirmPassword')
    );
  }

  get formIsInvalid() {
    return this.form.invalid || this.passwordsAreDifferent();
  }

  get passwordStyles() {
    return this.isInvalidPassword ? 'input-box -confirmpassword' : 'input-box';
  }

  ngOnDestroy(): void {
    if (this.formChangesSubscription)
      this.formChangesSubscription.unsubscribe();
  }

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

    this.formChangesSubscription = this.form.valueChanges.subscribe(() => {
      this.onFormChange.emit();
    });
  }

  handleSubmit({email, fullName, password, username}: CreateAccountFormData) {
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
    const {value} = event.target as HTMLInputElement;

    this.isValidConfirmPassword = value === this.form.get('password')?.value;
  }

  private passwordsAreDifferent() {
    const password = this.form.get('password');
    const confirmPassword = this.form.get('confirmPassword');

    if (password == null || confirmPassword == null) return true;

    return password.value !== confirmPassword.value;
  }
}
