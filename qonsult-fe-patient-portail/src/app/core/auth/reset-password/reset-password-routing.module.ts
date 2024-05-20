import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ResetPasswordComponent } from './components/reset-password/reset-password.component';
import { PasswordChangedComponent } from './components/password-changed/password-changed.component';
import { EnterMailComponent } from './components/enter-mail/enter-mail.component';
import { ResetPasswordSentComponent } from './components/reset-password-sent/reset-password-sent.component';

const routes: Routes = [
    {path: 'reset/:token',component: ResetPasswordComponent},
    {path: 'password-changed',component: PasswordChangedComponent},
    {path: 'enter-mail',component: EnterMailComponent},
    {path: 'reset-password-sent',component: ResetPasswordSentComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ResetPasswordRoutingModule { }
