import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, of } from 'rxjs';
import { Question } from '../models/question';
import { HttpUtilService } from 'src/app/util/service/http-util.service';
import { QuestionAnswer } from '../models/question-answer';
import { Certification } from '../models/certification.model';
import { QuestionnaireAnswer } from '../models/questionnaire-answer.model';
import { Questionnaire } from '../models/questionnaire';
import { QUESTIONNAIRE_LINK_LIST_MOCK } from '../../my-questionnaires/mock/questionnaire-list.mock';
import { QuestionnaireLink } from '../../my-questionnaires/model/quetionnaire-link-list';

const QUESTIONNAIRE_API_URL="/api/mediquest/public/questionnaire-request";
const QUESTIONNAIRE_RESPONSE_API_URL="/api/mediquest/public/questionnaire-response";

@Injectable({
  providedIn: 'root'
})


export class QuestionnaireService {

  constructor(private httpUtil:HttpUtilService) {
  }

  getQuestions(id:string){
    return this.httpUtil.get(`${QUESTIONNAIRE_API_URL}/${id}/questionnaire-model`);
    // return of(QUESTION_MOCK);
  }

  saveQuestionnaire(questionnaireRequstId:string,questionnaire:Questionnaire,generalInformations: any,questionsForm:any[],signature:string,appointmentDate:Date):Observable<QuestionnaireAnswer>{
    let questionnaireAnswer : any = {
        appointmentDate: appointmentDate,
        signature: signature,
        questionnaire: questionnaire,
        patientInformation: generalInformations,
        questionAnswers: this.formatQuestionsForm(questionsForm),
        questionnaireRequestId:questionnaireRequstId
    }
    return this.httpUtil.post(QUESTIONNAIRE_RESPONSE_API_URL,questionnaireAnswer);
  }

  formatQuestionsForm(form:any[]):QuestionAnswer[]{
    let result:QuestionAnswer[]=[];
    for (const [key, value] of Object.entries(form)) {
        let content = Array.isArray(value)? value: [value];
        result.push({
            questionId: key,
            content: content
        });
    }
    return result;
  }

  getQuestionnaireLinks():Observable<QuestionnaireLink[]>{
    return of(QUESTIONNAIRE_LINK_LIST_MOCK);
  }




}
