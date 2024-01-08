export interface CreateAccountResponseDto {
  token: string;
  username: string;
  fullName: string;
  expiresAt: Date;
}
