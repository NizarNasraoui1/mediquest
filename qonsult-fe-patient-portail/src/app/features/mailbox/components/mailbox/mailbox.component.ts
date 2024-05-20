import { Component, OnInit } from '@angular/core';
import { OrderListModule } from 'primeng/orderlist';
import { CalendarModule } from 'primeng/calendar';
import { DatePipe } from '@angular/common';
import { TagModule } from 'primeng/tag';
import { CommonModule } from '@angular/common';
import { MailboxService } from '../../services/mailbox.service';
import { Router } from '@angular/router';

@Component({
    standalone: true,
    imports: [OrderListModule, CalendarModule, CommonModule, TagModule],
    providers: [DatePipe],
    selector: 'app-mailbox',
    templateUrl: './mailbox.component.html',
    styleUrls: ['./mailbox.component.scss']
})
export class MailboxComponent implements OnInit {
    date: Date = new Date();
    mails: any[];
    constructor(private mailboxService:MailboxService,private router:Router) { }

    ngOnInit(): void {
        this.getReceivedQuestionnaires();
    }

    getReceivedQuestionnaires(){
        this.mailboxService.getMailsByDate(this.date).subscribe((res)=>{
            if(res.length!=0){
                this.mails = res;
            }
            else{
                this.mails = [{noResult:true}]
            }
        });
    }

    addDay(add:boolean) {
        let newDate = new Date(this.date);
        if(add) newDate.setDate(newDate.getDate() + 1);
        else newDate.setDate(newDate.getDate() - 1);
        this.date = newDate;
        this.getReceivedQuestionnaires();
      }

      navigateToViewQuestionnaire(id:number){
        this.router.navigate(['home/view-questionnaire/',id]);
      }


}
