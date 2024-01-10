import {Component, OnDestroy, OnInit} from '@angular/core';
import {LoginCredentialsDto} from "../../../infrastructure/http/requests/LoginCredentialsDto";
import {ChefClientService} from "../../../infrastructure/http/ChefClientService";
import {Observable} from "rxjs";
import {Store} from "@ngrx/store";
import {selectLoginErrors} from "../../../infrastructure/store/selectors/account.selectors";
import {Router} from "@angular/router";
import {ClearLoginErrorHandler} from "../../../infrastructure/store/handlers/ClearLoginErrorHandler";

@Component({
  selector: 'app-login-container',
  templateUrl: './login-container.component.html',
  styleUrl: './login-container.component.scss'
})
export class LoginContainerComponent implements OnInit, OnDestroy {
  errors$!: Observable<string[]>;

  constructor(private chefClient: ChefClientService, private store: Store, private router: Router, private clearLoginErrorHandle: ClearLoginErrorHandler) {
  }

  ngOnDestroy(): void {
    this.chefClient.unsubscribeLogin();
  }

  ngOnInit(): void {
    this.errors$ = this.store.select(selectLoginErrors);
  }

  handleLoginSubmit(data: LoginCredentialsDto) {
    this.chefClient.login(data, this.redirectToHome.bind(this));
  }

  handleFormChange() {
    this.clearLoginErrorHandle.handle();
  }

  private redirectToHome() {
    this.router.navigate(['/']);
  }
}
