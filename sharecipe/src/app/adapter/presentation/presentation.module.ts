import { NgModule } from '@angular/core';
import { InfrastructureModule } from '../infrastructure/infrastructure.module';
import { ContainersModule } from './containers/containers.module';

@NgModule({
  imports: [ContainersModule, InfrastructureModule],
})
export class PresentationModule {}
