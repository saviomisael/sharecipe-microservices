import { NgModule } from '@angular/core';
import { PresentersModule } from '../presenters/presenters.module';
import { CreateAccountContainerComponent } from './create-account-container/create-account-container.component';

@NgModule({
  declarations: [CreateAccountContainerComponent],
  imports: [PresentersModule],
  exports: [CreateAccountContainerComponent],
})
export class ContainersModule {}
