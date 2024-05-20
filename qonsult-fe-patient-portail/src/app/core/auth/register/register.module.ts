import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RegisterComponent } from './components/register/register.component';
import { ButtonModule } from 'primeng/button';
import { CheckboxModule } from 'primeng/checkbox';
import { FormsModule } from '@angular/forms';
import { PasswordModule } from 'primeng/password';
import { InputTextModule } from 'primeng/inputtext';
import { RegisternRoutingModule } from './register-routing.module';
import { ReactiveFormsModule } from '@angular/forms';
import { ConfirmMailComponent } from './components/confirm-mail/confirm-mail.component';
import { MailConfirmedComponent } from './components/mail-confirmed/mail-confirmed.component';

@NgModule({
  declarations: [
    RegisterComponent,
    ConfirmMailComponent,
    MailConfirmedComponent
  ],
  imports: [
    CommonModule,
    RegisternRoutingModule,
    ButtonModule,
    CheckboxModule,
    FormsModule,
    PasswordModule,
    InputTextModule,
    ReactiveFormsModule
  ]
})
export class RegisterModule { }
