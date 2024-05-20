import { Component } from '@angular/core';
import { LayoutService } from 'src/app/layout/service/app.layout.service';

@Component({
  selector: 'app-reset-password-sent',
  templateUrl: './reset-password-sent.component.html',
  styleUrls: ['./reset-password-sent.component.scss']
})
export class ResetPasswordSentComponent {
    constructor(public layoutService:LayoutService){}
}
