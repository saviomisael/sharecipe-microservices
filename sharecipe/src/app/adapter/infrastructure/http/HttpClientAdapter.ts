import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../environment/environment';
import { ResponseDto } from './responses/ResponseDto';

@Injectable()
export class HttpClientAdapter {
  constructor(private httpClient: HttpClient) {}

  post = <T, R>(apiUrl: string, body: T) =>
    this.httpClient.post<ResponseDto<R>>(`${environment.url}${apiUrl}`, body, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*',
      }),
    });
}
