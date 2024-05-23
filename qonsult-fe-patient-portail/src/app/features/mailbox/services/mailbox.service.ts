import { Injectable } from '@angular/core';
import { format } from 'date-fns';
import { HttpUtilService } from 'src/app/util/service/http-util.service';

const RECEIVED_QUESTIONNAIRES_API_URL = '/api/mediquest/received-questionnaires'

@Injectable({
  providedIn: 'root'
})
export class MailboxService {

  constructor(private httpUtil:HttpUtilService) { }

  getMailsByDate(date:Date){
    let params = {
        date : format(date,'dd/MM/yyyy')
    };
    return this.httpUtil.get(RECEIVED_QUESTIONNAIRES_API_URL,params);
  }
}
