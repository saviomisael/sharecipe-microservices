import {Chef} from '../../../../core/models/Chef';
import {LoginCredentialsDto} from "../requests/LoginCredentialsDto";

export interface IChefClientService {
  createAccount(chef: Chef, createdSuccess: () => void): void;

  unsubscribeCreateAccount(): void;

  login(data: LoginCredentialsDto, loginSuccess: () => void): void;

  unsubscribeLogin(): void;

  changePassword(password: string): Promise<void>;

  unsubscribeChangePassword(): void;

  changeUsername(username: string): Promise<void>;

  unsubscribeChangeUsername(): void;

  refreshToken(token: string): void;
}
