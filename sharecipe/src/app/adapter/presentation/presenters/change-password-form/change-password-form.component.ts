import {Component, EventEmitter, OnDestroy, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Subscription} from "rxjs";
import {isInvalidField} from "../../validators/isInvalidField";
import {passwordValidator} from "../../validators/passwordValidator";

interface ChangePasswordFormData {
  password: string
}

@Component({
  selector: 'app-change-password-form',
  templateUrl: './change-password-form.component.html',
  styleUrl: './change-password-form.component.scss'
})
export class ChangePasswordFormComponent implements OnInit, OnDestroy {
  private formGroup!: FormGroup;

  @Output() onChangePassword = new EventEmitter<string>();
  @Output() onFormChange = new EventEmitter();
  private formChangesSubscription: Subscription | null = null;

  constructor(private readonly formBuilder: FormBuilder) {
  }

  get isInvalidPassword() {
    return isInvalidField(this.formGroup, 'password');
  }

  get form() {
    return this.formGroup
  }

  ngOnDestroy(): void {
    if (this.formChangesSubscription)
      this.formChangesSubscription.unsubscribe();
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

    this.formChangesSubscription = this.formGroup.valueChanges.subscribe(() => {
      this.onFormChange.emit();
    });
  }

  handleSubmit({password}: ChangePasswordFormData) {
    if (this.formGroup.valid) {
      this.onChangePassword.emit(password);
    }
  }
}
