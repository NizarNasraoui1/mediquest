import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MailboxRoutingModule } from './mailbox-routing.module';
import { MailboxComponent } from './components/mailbox/mailbox.component';


@NgModule({
  declarations: [
  ],
  imports: [
    CommonModule,
    MailboxRoutingModule,
    MailboxComponent

  ]
})
export class MailboxModule { }
