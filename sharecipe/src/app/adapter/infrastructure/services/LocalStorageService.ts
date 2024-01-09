import {Injectable} from '@angular/core';

@Injectable()
export class LocalStorageService {
  saveCurrentUser(token: string, expiresAt: string, username: string) {
    localStorage.setItem('t', btoa(token));
    localStorage.setItem('e', btoa(expiresAt));
    localStorage.setItem('u', btoa(username));
  }

  getCurrentUser() {
    const token = localStorage.getItem('t');
    const expiresAt = localStorage.getItem('e');
    const username = localStorage.getItem('u');

    if (!token || !expiresAt || !username)
      return {token: null, expiresAt: null, username: null};

    return {
      token: atob(token),
      expiresAt: atob(expiresAt),
      username: atob(username),
    };
  }

  logout() {
    localStorage.removeItem('t')
    localStorage.removeItem('e')
    localStorage.removeItem('u')
  }
}
