import { Controller, Get } from '@nestjs/common';
import {
  HealthCheck,
  HealthCheckService,
  MongooseHealthIndicator,
} from '@nestjs/terminus';
import { MongoString } from '../../infrastructure/persistence/providers/MongoConnection.providers';

@Controller('api/v1/health')
export class HealthController {
  constructor(
    private healthService: HealthCheckService,
    private mongoose: MongooseHealthIndicator,
  ) {}

  @Get('mongo')
  @HealthCheck()
  check() {
    return this.healthService.check([
      async () =>
        this.mongoose.pingCheck('mongoose', {
          connection: MongoString,
          timeout: 3000,
        }),
    ]);
  }
}
