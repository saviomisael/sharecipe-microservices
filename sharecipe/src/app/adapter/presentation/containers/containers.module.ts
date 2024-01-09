import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {PresentersModule} from '../presenters/presenters.module';
import {CreateAccountContainerComponent} from './create-account-container/create-account-container.component';
import {MainHeaderWrapperComponent} from "./main-header-wrapper/main-header-wrapper.component";
import {HomeContainerComponent} from "./home-container/home-container.component";
import {InfrastructureModule} from "../../infrastructure/infrastructure.module";

@NgModule({
  declarations: [CreateAccountContainerComponent, MainHeaderWrapperComponent, HomeContainerComponent],
  imports: [PresentersModule, CommonModule, InfrastructureModule],
  exports: [CreateAccountContainerComponent, HomeContainerComponent],
})
export class ContainersModule {
}
