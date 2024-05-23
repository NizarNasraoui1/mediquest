import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginForm } from 'src/app/shared/models/loginForm';
import { TokenStorageService } from './token-storage.service';
import { HttpUtilService } from 'src/app/util/service/http-util.service';
import { Register } from 'src/app/shared/models/register';
import { User } from 'src/app/shared/models/user';
import { Center } from 'src/app/shared/models/center';
import { ResetPassword } from '../auth/reset-password/models/reset-password.model';

export interface loginResponse{
    accessToken:string,
    refreshToken:string,
    tenant:string
}

const AuthUrl = 'api/mediquest/public/auth';

@Injectable({
    providedIn: 'root',
})
export class AuthService {
    constructor(
        private http: HttpClient,
        private tokenStorageService: TokenStorageService,
        private httpUtilService: HttpUtilService
    ) {}

    login(loginForm: LoginForm): Observable<any> {;
        return this.httpUtilService.post(`${AuthUrl}/login`,loginForm);
    }

    logOut() {
        this.tokenStorageService.signOut();
    }

    isAuthenticated(): boolean {
        const token = this.tokenStorageService.getToken();
        if (!token) return false;
        return true;
    }

    register(user:User,center:Center){
        const register: Register = {
            user:user,
            center:center
        }
        return this.httpUtilService.post(`${AuthUrl}/register`,register);
    }

    confirmMail(token:string){
        const params = {token:token};
        return this.httpUtilService.post(`${AuthUrl}/mail-confirmation`,null,params);
    }

    saveTokens(loginResponse:loginResponse){
        this.tokenStorageService.saveAccessToken(loginResponse.accessToken);
        this.tokenStorageService.saveRefreshToken(loginResponse.refreshToken);
        this.tokenStorageService.saveTenant(loginResponse.tenant);
    }

    getAccessTokenFromRefreshToken(){
        return this.httpUtilService.post(`${AuthUrl}/token`,this.tokenStorageService.getRefreshToken());
    }

    public isTokenExpired(token: string): boolean {
        const decodedToken = atob(token.split('.')[1]);
        const parsedToken = JSON.parse(decodedToken);

        if (!parsedToken.exp || typeof parsedToken.exp !== 'number') {
          throw new Error('Invalid token: Missing or invalid expiration claim');
        }

        const currentTime = Math.floor(Date.now() / 1000);
        return currentTime >= parsedToken.exp;
      }

    resetPassword(password:string,token:string){
        const resetPassword = {
            resetPasswordToken:token,
            password: password
        }
        return this.httpUtilService.post(`${AuthUrl}/reset-password`,resetPassword);
    }

    sendResetPasswordMail(email:string){
        return this.httpUtilService.post(`${AuthUrl}/forgot-password`,email);
    }
}
