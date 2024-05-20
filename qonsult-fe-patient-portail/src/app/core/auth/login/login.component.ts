import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/core/_services/auth.service';
import { TokenStorageService } from 'src/app/core/_services/token-storage.service';
import { LayoutService } from 'src/app/layout/service/app.layout.service';
import { LoginForm } from 'src/app/shared/models/loginForm';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
    form: any = {};
    isLoggedIn = false;
    isLoginFailed = false;
    errorMessage = '';
    roles: string[] = [];
    username!: string;
    password!: string;

    constructor(
        public layoutService: LayoutService,
        private authService: AuthService,
        private tokenStorage: TokenStorageService,
        private router: Router,
    ) {}
    ngOnInit(): void {
        if (this.tokenStorage.getToken()) {
            this.isLoggedIn = true;
            // this.roles = this.tokenStorage.getUser().roles;
        }
    }

    onSubmit(): void {
        const loginForm = new LoginForm(this.username, this.password);
        this.authService.login(loginForm).subscribe(
            (data) => {
                this.authService.saveTokens(data);
                // this.tokenStorage.saveRefreshToken(data.refresh_token);
                // this.tokenStorage.saveUser(data);
                this.isLoginFailed = false;
                this.isLoggedIn = true;
                // this.roles = this.tokenStorage.getUser().roles;
                this.navigateToMainPage();
            },
            (err) => {
                this.isLoginFailed = true;
            },
        );
    }



    navigateToMainPage(): void {
        this.router.navigate(['/home']);
    }
}
