import {Component, EventEmitter, OnDestroy, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Subscription} from "rxjs";
import {isInvalidField} from "../../validators/isInvalidField";
import {passwordValidator} from "../../validators/passwordValidator";
import {fadeInOutAnimation} from "../../animations/fadeInOutAnimation";

interface ChangePasswordFormData {
  password: string
}

@Component({
  selector: 'app-change-password-form',
  templateUrl: './change-password-form.component.html',
  styleUrl: './change-password-form.component.scss',
  animations: [fadeInOutAnimation]
})
export class ChangePasswordFormComponent implements OnInit, OnDestroy {
  @Output() onChangePassword = new EventEmitter<string>();
  private formGroup!: FormGroup;
  private formChangesSubscription: Subscription | null = null;

  constructor(private readonly formBuilder: FormBuilder) {
  }

  get isInvalidPassword() {
    return isInvalidField(this.formGroup, 'password');
  }

  get form() {
    return this.formGroup
  }

  get buttonStyles() {
    return this.isInvalidPassword ? 'change-password-submit -error' : 'change-password-submit'
  }

  ngOnDestroy(): void {
    this.formChangesSubscription?.unsubscribe();
  }

  ngOnInit(): void {
    this.formGroup = this.formBuilder.group({
      password: [
        '',
        [
          Validators.required,
          Validators.minLength(8),
          Validators.maxLength(255),
          passwordValidator,
        ],
      ],
    });
  }

  handleSubmit({password}: ChangePasswordFormData) {
    if (this.formGroup.valid) {
      this.onChangePassword.emit(password);
    }
  }
}
