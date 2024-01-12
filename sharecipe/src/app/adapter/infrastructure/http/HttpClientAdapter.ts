import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {environment} from '../environment/environment';
import {ResponseDto} from './responses/ResponseDto';

@Injectable()
export class HttpClientAdapter {
  constructor(private httpClient: HttpClient) {
  }

  post = <B, R>(apiUrl: string, body: B) =>
    this.httpClient.post<ResponseDto<R>>(`${environment.url}${apiUrl}`, body, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*',
      }),
    });

  patchWithAuth = <B, R>(apiUrl: string, body: B, token: string) => this.httpClient.patch<ResponseDto<R>>(`${environment.url}${apiUrl}`, body, {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*',
      'Authorization': `Bearer ${token}`
    }),
  })
}
