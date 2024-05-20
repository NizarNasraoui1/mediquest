import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ResetPasswordRoutingModule } from './reset-password-routing.module';
import { ResetPasswordComponent } from './components/reset-password/reset-password.component';
import { PasswordChangedComponent } from './components/password-changed/password-changed.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RegisternRoutingModule } from '../register/register-routing.module';
import { ButtonModule } from 'primeng/button';
import { CheckboxModule } from 'primeng/checkbox';
import { PasswordModule } from 'primeng/password';
import { InputTextModule } from 'primeng/inputtext';
import { EnterMailComponent } from './components/enter-mail/enter-mail.component';
import { ResetPasswordSentComponent } from './components/reset-password-sent/reset-password-sent.component';


@NgModule({
  declarations: [
    ResetPasswordComponent,
    PasswordChangedComponent,
    EnterMailComponent,
    ResetPasswordSentComponent
  ],
  imports: [
    CommonModule,
    ResetPasswordRoutingModule,
    ButtonModule,
    CheckboxModule,
    FormsModule,
    PasswordModule,
    InputTextModule,
    ReactiveFormsModule
  ]
})
export class ResetPasswordModule { }
