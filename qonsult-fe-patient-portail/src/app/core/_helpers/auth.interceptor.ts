import { Injectable, Inject } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse,
  HTTP_INTERCEPTORS,
} from '@angular/common/http';
import { Observable, throwError, from, EMPTY } from 'rxjs';
import { catchError, switchMap } from 'rxjs/operators';
import { TokenStorageService } from '../_services/token-storage.service';
import { AuthService } from '../_services/auth.service';
import { Router } from '@angular/router';
import { DOCUMENT } from '@angular/common';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  private isRefreshing = false;

  constructor(
    private tokenService: TokenStorageService,
    private authService: AuthService,
    private router: Router,
    @Inject(DOCUMENT) private document: Document
  ) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = this.tokenService.getToken();
    const tenant = this.tokenService.getTenant();
    if (token) {
      req = this.addAuthorizationHeader(req, token,tenant);
    }

    return next.handle(req).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === 403 && !req.url.includes('public')) {
          return this.handle403Error(req, next);
        } else {
          return throwError(() => error);
        }
      })
    );
  }

  private addAuthorizationHeader(request: HttpRequest<any>, token: string,tenant:string): HttpRequest<any> {
    return request.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`,
        Tenant:tenant
    }
    });
  }

  private handle403Error(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (!this.isRefreshing) {
      this.isRefreshing = true;
      return this.authService.getAccessTokenFromRefreshToken().pipe(
        switchMap((updatedToken) => {
          this.isRefreshing = false;
          this.tokenService.saveAccessToken(updatedToken.accessToken);
          this.tokenService.saveRefreshToken(updatedToken.refreshToken);
          this.tokenService.saveTenant(updatedToken.tenant);
          return next.handle(this.addAuthorizationHeader(request, updatedToken.accessToken,updatedToken.tenant));
        }),
        catchError((error) => {
          this.isRefreshing = false;
          this.handleRefreshTokenFailure();
          return EMPTY;
        })
      );
    } else {
      return EMPTY;
    }
  }

  private handleRefreshTokenFailure(): void {
    this.authService.logOut();
    this.router.navigate(['/auth/login']);
  }
}

export const authInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
];
