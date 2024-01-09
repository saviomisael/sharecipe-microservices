import { Component } from '@angular/core';
import {Store} from "@ngrx/store";
import {selectUsername} from "../../../infrastructure/store/selectors/account.selectors";
import {Observable} from "rxjs";

@Component({
  selector: 'app-main-header-wrapper',
  templateUrl: './main-header-wrapper.component.html',
  styleUrl: './main-header-wrapper.component.scss'
})
export class MainHeaderWrapperComponent {
  constructor(private store: Store) {
  }

  get username$(): Observable<string> {
    return this.store.select(selectUsername)
  }
}
