import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { RegisterComponent } from './components/register/register.component';
import { ConfirmMailComponent } from './components/confirm-mail/confirm-mail.component';
import { MailConfirmedComponent } from './components/mail-confirmed/mail-confirmed.component';

@NgModule({
    imports: [RouterModule.forChild([
        { path: '', component: RegisterComponent },
        {path: 'confirm-mail',component: ConfirmMailComponent},
        {path: 'mail-confirmed',component: MailConfirmedComponent}
    ])],
    exports: [RouterModule],
})
export class RegisternRoutingModule {}
