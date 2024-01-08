import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { ChefClientService } from './http/ChefClientService';
import { HttpClientAdapter } from './http/HttpClientAdapter';

@NgModule({
  imports: [HttpClientModule],
  providers: [ChefClientService, HttpClientAdapter],
})
export class InfrastructureModule {}
