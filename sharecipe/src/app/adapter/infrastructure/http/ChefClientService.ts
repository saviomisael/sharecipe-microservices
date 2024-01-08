import { Injectable } from '@angular/core';
import { Subscription, catchError } from 'rxjs';
import { Chef } from '../../../core/models/Chef';
import { HttpClientAdapter } from './HttpClientAdapter';
import { IChefClientService } from './contracts/IChefClientService';
import { CreateAccountResponseDto } from './responses/CreateAccountResponseDto';
import { ResponseDto } from './responses/ResponseDto';

@Injectable()
export class ChefClientService implements IChefClientService {
  private createAccountSubscription: Subscription | null = null;

  constructor(private httpClient: HttpClientAdapter) {}

  createAccount(
    chef: Chef
  ): ResponseDto<CreateAccountResponseDto> | ResponseDto<null> {
    this.createAccountSubscription = this.httpClient
      .post<Chef, CreateAccountResponseDto>('/api/v1/chefs/', chef)
      .pipe(
        catchError((error, caught) => {
          console.log('ERRO', error);
          return caught;
        })
      )
      .subscribe({
        next: (response) => console.log('NEXT', response),
      });

    return {
      data: null,
      errors: [],
    };
  }

  unsubscribeCreateAccount() {
    if (this.createAccountSubscription) {
      this.createAccountSubscription.unsubscribe();
    }
  }
}
