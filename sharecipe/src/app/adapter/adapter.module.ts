import { NgModule } from '@angular/core';
import { InfrastructureModule } from './infrastructure/infrastructure.module';
import { PresentationModule } from './presentation/presentation.module';

@NgModule({
  imports: [PresentationModule, InfrastructureModule],
})
export class AdapterModule {}
