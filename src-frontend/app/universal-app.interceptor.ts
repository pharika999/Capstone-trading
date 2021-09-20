import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { LocalStorageService } from './services/localStorage.service';

@Injectable()
export class UniversalAppInterceptor implements HttpInterceptor {

  constructor(private storageService:LocalStorageService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const token = this.storageService.get('token');
    console.log("Token in Interceptor",token);

    if(request.url.includes('/login/authenticate')){
        return next.handle(request);
    }
    request = request.clone({
      url:  request.url,
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });
    return next.handle(request);
  }
}
