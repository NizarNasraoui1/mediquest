import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './auth.service';
import { TokenStorageService } from './token-storage.service';

@Injectable({
    providedIn: 'root',
})
export class AuthGardService {
    constructor(
        private authService: AuthService,
        private router: Router,
    ) {}

    canActivate(): boolean {
        if (!this.authService.isAuthenticated()) {
            this.router.navigate(['./auth/login']);
            return false;
        }
        return true;
    }
}
