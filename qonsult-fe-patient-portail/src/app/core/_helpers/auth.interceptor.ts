import { HTTP_INTERCEPTORS, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError, BehaviorSubject } from 'rxjs';
import { catchError, switchMap, filter, take } from 'rxjs/operators';
import { TokenStorageService } from '../_services/token-storage.service';
import { AuthService } from '../_services/auth.service';

const TOKEN_HEADER_KEY = 'Authorization';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
    private isRefreshing = false;
    private refreshTokenSubject: BehaviorSubject<any> = new BehaviorSubject<any>(null);

    constructor(private token: TokenStorageService, private authService: AuthService) {}

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        let authReq = req;
        const token = this.token.getToken();
        const tenant = this.token.getTenant();

        if (token && !authReq.url.includes('/public')) {
            authReq = this.addTokenToHeader(authReq, token, tenant);
        }

        return next.handle(authReq).pipe(catchError(error => {
            if (error.error.error_message.includes('The Token has expired') && token) {
                return this.handle401Error(authReq, next);
            }
            return throwError(error);
        }));
    }

    private addTokenToHeader(request: HttpRequest<any>, token: string, tenant: string) {
        return request.clone({
            setHeaders: {
                [TOKEN_HEADER_KEY]: 'Bearer ' + token,
                Tenant: tenant
            }
        });
    }

    private handle401Error(request: HttpRequest<any>, next: HttpHandler) {
        if (!this.isRefreshing) {
            this.isRefreshing = true;
            this.refreshTokenSubject.next(null);

            return this.authService.getAccessTokenFromRefreshToken().pipe(
                switchMap((token: any) => {
                    this.isRefreshing = false;
                    this.refreshTokenSubject.next(token.refreshToken);
                    this.token.saveAccessToken(token.accessToken);
                    return next.handle(this.addTokenToHeader(request, token.accessToken, request.headers.get('Tenant')));
                })
            );
        } else {
            return this.refreshTokenSubject.pipe(
                filter(token => token != null),
                take(1),
                switchMap(jwt => {
                    return next.handle(this.addTokenToHeader(request, jwt, request.headers.get('Tenant')));
                })
            );
        }
    }
}

export const authInterceptorProviders = [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
];
