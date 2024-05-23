import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpUtilService } from 'src/app/util/service/http-util.service';
import { QuestionnaireLink } from '../model/quetionnaire-link-list';

const MY_QUESTIONNAIRE_API_URL="/api/mediquest/qr-codes/all";

@Injectable({
  providedIn: 'root'
})
export class QuestionnairesLinkService {

  constructor(private http:HttpClient,private httpUtil:HttpUtilService) { }

  getMyQuestionnairesLinks():Observable<QuestionnaireLink[]>{
    return this.httpUtil.get(`${MY_QUESTIONNAIRE_API_URL}`);
  }
}
