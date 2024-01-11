import {Component, EventEmitter, OnDestroy, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Subscription} from "rxjs";
import {isInvalidField} from "../../validators/isInvalidField";

interface ChangeUsernameFormData {
  username: string
}

@Component({
  selector: 'app-change-username-form',
  templateUrl: './change-username-form.component.html',
  styleUrl: './change-username-form.component.scss'
})
export class ChangeUsernameFormComponent implements OnInit, OnDestroy {
  @Output() onChangeUsername = new EventEmitter<string>();
  @Output() onFormChange = new EventEmitter();
  private formGroup!: FormGroup;
  private formChangesSubscription: Subscription | null = null;

  constructor(private readonly formBuilder: FormBuilder) {
  }

  get form() {
    return this.formGroup
  }

  get isInvalidUsername() {
    return isInvalidField(this.form, 'username');
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
      ],
    });

    this.formChangesSubscription = this.formGroup.valueChanges.subscribe(() => {
      this.onFormChange.emit();
    });
  }

  ngOnDestroy(): void {
    this.formChangesSubscription?.unsubscribe();
  }

  handleSubmit({username}: ChangeUsernameFormData) {
    if (this.formGroup.valid) {
      this.onChangeUsername.emit(username);
    }
  }
}
