import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MailboxComponent } from './components/mailbox/mailbox.component';

const routes: Routes = [
    {path: '',component:MailboxComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MailboxRoutingModule { }
