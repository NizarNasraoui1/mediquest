import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { LayoutService } from 'src/app/layout/service/app.layout.service';
import { AuthService } from '../../../../_services/auth.service';
import { TokenStorageService } from '../../../../_services/token-storage.service';
import { Router } from '@angular/router';
import { LoginForm } from 'src/app/shared/models/loginForm';
import { FormBuilder, FormControl } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class RegisterComponent implements OnInit {
    form: any = {};
    userForm:any = {};
    centerForm:any = {};
    isLoggedIn = false;
    isLoginFailed = false;
    errorMessage = new FormControl('');
    roles: string[] = [];
    username!: string;
    password!: string;

    constructor(
        public layoutService: LayoutService,
        private authService: AuthService,
        private router: Router,
        private fb:FormBuilder
    ) {}

    ngOnInit(): void {
        this.initForm();
    }

    initForm(){
        this.userForm=this.fb.group({
            id: new FormControl(''),
            firstName: new FormControl(''),
            lastName: new FormControl(''),
            username: new FormControl(''),
            password: new FormControl(''),
            email: new FormControl('')
        });
        this.centerForm = this.fb.group({
            name: new FormControl(''),
            address: new FormControl('')
        })
    }


    onSubmit(): void {
        this.authService.register(this.userForm.value,this.centerForm.value).subscribe((res)=>{
            this.navigateToConfirmMail();
        });
    }

    navigateToConfirmMail(): void {
        this.router.navigate(['auth/register/confirm-mail']);
    }
}
