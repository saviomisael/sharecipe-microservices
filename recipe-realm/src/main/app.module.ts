import { Module } from '@nestjs/common';
import { EurekaModule } from 'nestjs-eureka';
import { PresentationModule } from 'src/core/adapter/presentation/presentation.module';
import { AppController } from '../app.controller';
import { InfrastructureModule } from '../core/adapter/infrastructure/infrastructure.module';

@Module({
  imports: [
    EurekaModule.forRoot({
      eureka: {
        host: 'localhost',
        port: 8761,
        registryFetchInterval: 1000,
        servicePath: '/eureka/apps/',
        maxRetries: 3,
      },
      service: {
        name: 'recipe-realm',
        port: process.env.PORT ? Number(process.env.PORT) : 3000,
      },
    }),
    InfrastructureModule,
    PresentationModule,
  ],
  controllers: [AppController],
  providers: [],
})
export class AppModule {}
