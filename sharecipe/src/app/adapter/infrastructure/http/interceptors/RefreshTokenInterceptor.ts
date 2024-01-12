import {Injectable} from "@angular/core";
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";
import {ChefClientService} from "../ChefClientService";
import {LocalStorageService} from "../../services/LocalStorageService";


@Injectable()
export class RefreshTokenInterceptor implements HttpInterceptor {
  constructor(private chefClient: ChefClientService, private localService: LocalStorageService) {
  }

  intercept(req: HttpRequest<any>, nextHandler: HttpHandler): Observable<HttpEvent<any>> {
    const token = this.localService.getCurrentUser().token;

    if (token != null && this.localService.isLoggedIn() && !this.isPublicUrl(req.url)) {
      this.chefClient.refreshToken(token);
    }

    return nextHandler.handle(req)
  }

  private isPublicUrl(url: string): boolean {
    const publicUrls = ['/api/v1/chefs/', '/api/v1/chefs/tokens/', '/api/v1/chefs/refresh-tokens/'];

    return publicUrls.some((value) => url.endsWith(value));
  }
}
