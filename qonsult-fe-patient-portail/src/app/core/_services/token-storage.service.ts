import { Injectable } from '@angular/core';

const ACCESS_TOKEN_KEY = 'auth-access-token';
const USER_KEY = 'auth-user';
const REFRESH_TOKEN_KEY = 'auth-refresh-token';
const TENANT = 'tenant';

@Injectable({
    providedIn: 'root',
})
export class TokenStorageService {
    constructor() {}

    signOut(): void {
        window.localStorage.clear();
    }

    public saveAccessToken(token: string): void {
        window.localStorage.removeItem(ACCESS_TOKEN_KEY);
        window.localStorage.setItem(ACCESS_TOKEN_KEY, token);
    }

    public saveRefreshToken(accessToken: string): void {
        window.localStorage.removeItem(REFRESH_TOKEN_KEY);
        window.localStorage.setItem(REFRESH_TOKEN_KEY, accessToken);
    }

    public getToken(): string {
        let token = localStorage.getItem(ACCESS_TOKEN_KEY);
        return token ? token : '';
    }

    public getRefreshToken(): string {
        let token = localStorage.getItem(REFRESH_TOKEN_KEY);
        return token ? token : '';
    }

    public saveUser(user: any): void {
        window.localStorage.removeItem(USER_KEY);
        window.localStorage.setItem(USER_KEY, JSON.stringify(user));
    }

    public getUser(): any {
        let user = localStorage.getItem(USER_KEY);
        return user ? JSON.parse(user) : null;
    }

    public saveTenant(tenant:string){
        window.localStorage.removeItem(TENANT);
        window.localStorage.setItem(TENANT, JSON.stringify(tenant));
    }

    public getTenant(): any {
        let tenant = localStorage.getItem(TENANT);
        return tenant ? JSON.parse(tenant) : null;
    }
}
