import { Injectable } from '@angular/core';
import { HttpUtilService } from 'src/app/util/service/http-util.service';

const QUESTIONNAIRE_NAMES_URL="/api/mediquest/questionnaire-model/names-links";
const QUESTIONNAIRE_INVITATION_API_URL= "/api/questionnaire-invitation";

@Injectable({
  providedIn: 'root'
})
export class SendingBoxService {

  constructor(private httpUtil:HttpUtilService) { }

  getQuestionnaireNames(){
    return this.httpUtil.get(QUESTIONNAIRE_NAMES_URL);
  }

  sendQuestionnaireInvitation(questionnaireInvitation:any){
    return this.httpUtil.post(QUESTIONNAIRE_INVITATION_API_URL,questionnaireInvitation);
  }
}
