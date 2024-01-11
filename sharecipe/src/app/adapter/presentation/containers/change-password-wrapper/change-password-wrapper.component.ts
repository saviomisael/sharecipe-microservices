import {Component, OnDestroy, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {Store} from "@ngrx/store";
import {
  selectChangePasswordErrors,
  selectShowChangePasswordSuccessMessage
} from "../../../infrastructure/store/selectors/account.selectors";
import {ChefClientService} from "../../../infrastructure/http/ChefClientService";
import {
  ClearChangePasswordErrorsAndMessageHandler
} from "../../../infrastructure/store/handlers/ClearChangePasswordErrorsAndMessageHandler";
import {fadeInOutAnimation} from "../../animations/fadeInOutAnimation";

@Component({
  selector: 'app-change-password-wrapper',
  templateUrl: './change-password-wrapper.component.html',
  styleUrl: './change-password-wrapper.component.scss',
  animations: [fadeInOutAnimation]
})
export class ChangePasswordWrapperComponent implements OnInit, OnDestroy {
  error$!: Observable<string>;
  showSuccessMessage$!: Observable<boolean>; // for alert component

  constructor(private store: Store, private chefClient: ChefClientService, private clearChangePasswordErrorsAndMessageHandler: ClearChangePasswordErrorsAndMessageHandler) {
  }

  ngOnInit(): void {
    this.error$ = this.store.select(selectChangePasswordErrors);
    this.showSuccessMessage$ = this.store.select(selectShowChangePasswordSuccessMessage);
  }

  ngOnDestroy(): void {
    this.chefClient.unsubscribeChangePassword();
  }

  handleChangePassword(newPassword: string) {
    this.chefClient.changePassword(newPassword);
  }

  handleAlertClose() {
    this.clearChangePasswordErrorsAndMessageHandler.handle();
  }
}
