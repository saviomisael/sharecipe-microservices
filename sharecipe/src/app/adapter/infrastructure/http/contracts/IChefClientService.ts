import { Chef } from '../../../../core/models/Chef';

export interface IChefClientService {
  createAccount(chef: Chef, createdSuccess: () => void): void;
  unsubscribeCreateAccount(): void;
}
