import { Injectable } from '@angular/core';
import { HttpUtilService } from 'src/app/util/service/http-util.service';

const SAVE_QUESTIONNAIRE_URL = '/api/mediquest/questionnaire-builder';
@Injectable({
  providedIn: 'root'
})
export class QuestionnaireBuilderService {

  constructor(private httpUtil:HttpUtilService) { }

  saveQuestionnaire(questionnaire){
    return this.httpUtil.post(SAVE_QUESTIONNAIRE_URL,questionnaire);
  }
}
