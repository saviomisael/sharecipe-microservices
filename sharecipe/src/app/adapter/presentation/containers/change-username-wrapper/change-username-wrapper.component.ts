import {Component, OnDestroy, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {Store} from "@ngrx/store";
import {
  selectShowChangeUsernameError,
  selectShowChangeUsernameSuccessMessage
} from "../../../infrastructure/store/selectors/account.selectors";
import {fadeInOutAnimation} from "../../animations/fadeInOutAnimation";
import {
  ClearChangeUsernameErrorAndMessageHandler
} from "../../../infrastructure/store/handlers/ClearChangeUsernameErrorAndMessageHandler";
import {ChefClientService} from "../../../infrastructure/http/ChefClientService";

@Component({
  selector: 'app-change-username-wrapper',
  templateUrl: './change-username-wrapper.component.html',
  styleUrl: './change-username-wrapper.component.scss',
  animations: [fadeInOutAnimation]
})
export class ChangeUsernameWrapperComponent implements OnInit, OnDestroy {
  error$!: Observable<boolean>;
  success$!: Observable<boolean>;

  constructor(private store: Store, private clearChangeUsernameErrorHandler: ClearChangeUsernameErrorAndMessageHandler, private chefClient: ChefClientService) {
  }

  ngOnInit(): void {
    this.error$ = this.store.select(selectShowChangeUsernameError);
    this.success$ = this.store.select(selectShowChangeUsernameSuccessMessage);
  }

  ngOnDestroy(): void {
    this.chefClient.unsubscribeChangeUsername();
  }

  handleAlertClose() {
    this.clearChangeUsernameErrorHandler.handle();
  }

  handleChangeUsername(newUsername: string) {
    this.chefClient.changeUsername(newUsername);
  }
}
