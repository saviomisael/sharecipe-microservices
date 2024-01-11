import {Component, OnDestroy, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {Store} from '@ngrx/store';
import {Observable} from 'rxjs';
import {Chef} from '../../../../core/models/Chef';
import {ChefClientService} from '../../../infrastructure/http/ChefClientService';
import {ClearCreateAccountErrorsHandler} from '../../../infrastructure/store/handlers/ClearCreateAccountErrorsHandler';
import {selectCreateAccountErrors} from '../../../infrastructure/store/selectors/account.selectors';

@Component({
  selector: 'app-create-account-container',
  templateUrl: './create-account-container.component.html',
  styleUrl: './create-account-container.component.scss',
})
export class CreateAccountContainerComponent implements OnDestroy, OnInit {
  errors$!: Observable<string[]>;

  constructor(
    private chefClientService: ChefClientService,
    private store: Store,
    private clearCreateAccountErrorsHandler: ClearCreateAccountErrorsHandler,
    private router: Router
  ) {
  }

  ngOnInit(): void {
    this.errors$ = this.store.select(selectCreateAccountErrors);
  }

  ngOnDestroy(): void {
    this.chefClientService.unsubscribeCreateAccount();
  }

  handleCreateAccountSubmit(data: Chef) {
    this.chefClientService.createAccount(data, this.redirectToHome.bind(this));
  }

  handleFormChange() {
    this.clearCreateAccountErrorsHandler.handle();
  }

  private redirectToHome() {
    this.router.navigate(['/']);
  }
}
