import { Component } from '@angular/core';
import { FormBuilder, FormControl } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/core/_services/auth.service';
import { LayoutService } from 'src/app/layout/service/app.layout.service';

@Component({
  selector: 'app-enter-mail',
  templateUrl: './enter-mail.component.html',
  styleUrls: ['./enter-mail.component.scss']
})
export class EnterMailComponent {
    form: any = {};
    isLoggedIn = false;
    isNotValidEmail = false;
    errorMessage: string;
    token: string;

    constructor(
        public layoutService: LayoutService,
        private authService: AuthService,
        private router: Router,
        private fb: FormBuilder
    ) {}

    ngOnInit(): void {
        this.initForm();
    }

    initForm() {
        this.form = this.fb.group({
            email: new FormControl('')
        });
    }

    arePasswordsAreEquals(password: string, confirmation: string): boolean {
        if (password != confirmation) {
            this.errorMessage = 'Les mots de passes ne sont pas identiques';
            return false;
        }
        return true;
    }

    isValidEmail() {
        return true;
    }

    onSubmit(): void {
        if (this.isValidEmail()) {
            this.authService
                .sendResetPasswordMail(this.form.value.email)
                .subscribe(() => {
                    this.router.navigate([
                        'auth/reset-password/reset-password-sent',
                    ]);
                });
        } else {
            this.isNotValidEmail = true;
        }
    }

    navigateToConfirmMail(): void {
        this.router.navigate(['auth/register/confirm-mail']);
    }
}
