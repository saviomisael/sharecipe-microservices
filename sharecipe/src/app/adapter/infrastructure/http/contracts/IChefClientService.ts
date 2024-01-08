import { Chef } from '../../../../core/models/Chef';

export interface IChefClientService {
  createAccount(chef: Chef): void;
  unsubscribeCreateAccount(): void;
}
