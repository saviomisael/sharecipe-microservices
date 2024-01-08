import { Chef } from '../../../../core/models/Chef';
import { CreateAccountResponseDto } from '../responses/CreateAccountResponseDto';
import { ResponseDto } from '../responses/ResponseDto';

export interface IChefClientService {
  createAccount(
    chef: Chef
  ): ResponseDto<CreateAccountResponseDto> | ResponseDto<null>;
  unsubscribeCreateAccount(): void;
}
