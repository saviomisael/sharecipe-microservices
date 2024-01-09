import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {PresentersModule} from '../presenters/presenters.module';
import {CreateAccountContainerComponent} from './create-account-container/create-account-container.component';
import {MainHeaderWrapperComponent} from "./main-header-wrapper/main-header-wrapper.component";

@NgModule({
  declarations: [CreateAccountContainerComponent, MainHeaderWrapperComponent],
  imports: [PresentersModule, CommonModule],
  exports: [CreateAccountContainerComponent, MainHeaderWrapperComponent],
})
export class ContainersModule {
}
