import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from 'src/app/core/_services/auth.service';
import { LayoutService } from 'src/app/layout/service/app.layout.service';

@Component({
  selector: 'app-mail-confirmed',
  templateUrl: './mail-confirmed.component.html',
  styleUrls: ['./mail-confirmed.component.scss']
})
export class MailConfirmedComponent implements OnInit {

  constructor(public layoutService:LayoutService,private route: ActivatedRoute,private authService:AuthService) { }

  ngOnInit(): void {
    this.confirmMail();
  }

  confirmMail(){
    this.route.queryParams.subscribe((params)=>{
        this.authService.confirmMail(params['token']).subscribe(()=>{
        })
    });
  }

}
