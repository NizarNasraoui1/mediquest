import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, of } from 'rxjs';
import { Question } from '../models/question';
import { QUESTION_MOCK } from '../models/mock-questions';
import { HttpUtilService } from 'src/app/util/service/http-util.service';
import { QuestionAnswer } from '../models/question-answer';
import { Certification } from '../models/certification.model';
import { QuestionnaireAnswer } from '../models/questionnaire-answer.model';
import { Questionnaire } from '../models/questionnaire';
import { QUESTIONNAIRE_LINK_LIST_MOCK } from '../../my-questionnaires/mock/questionnaire-list.mock';
import { QuestionnaireLink } from '../../my-questionnaires/model/quetionnaire-link-list';

const QUESTIONNAIRE_API_URL="/api/mediquest/public/questionnaire-request";
const questionnaireAnswerApiUrl="/api/public/questionnaire-answer/";

@Injectable({
  providedIn: 'root'
})


export class QuestionnaireService {
  private questionsSubject = new BehaviorSubject<Question[]>(QUESTION_MOCK);
  questions$ = this.questionsSubject.asObservable();

  constructor(private httpUtil:HttpUtilService) {
  }

  getQuestions(id:string){
    return this.httpUtil.get(`${QUESTIONNAIRE_API_URL}/${id}/questionnaire-model`);
    // return of(QUESTION_MOCK);
  }

  saveQuestionnaire(questionnaire:Questionnaire,generalInformations: any,questionsForm:any[],certificationForm:any,appointmentDate:Date):Observable<QuestionnaireAnswer>{
    let questionnaireAnswer : any = {
        appointmentDate: appointmentDate,
        certification: this.formatCertificationForm(certificationForm),
        questionnaire: questionnaire,
        patientInformation: generalInformations,
        questionAnswers: this.formatQuestionsForm(questionsForm)
    }
    return this.httpUtil.post(questionnaireAnswerApiUrl,questionnaireAnswer);
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

  formatCertificationForm(certificationForm:any):Certification{
    return {
        approved: certificationForm.certification.length>0,
        filledBy:certificationForm.filledBy,
        clickX:certificationForm.signature.clickX,
        clickY:certificationForm.signature.clickY,
        clickDrag:certificationForm.signature.clickDrag
    }
  }

  getQuestionnaireLinks():Observable<QuestionnaireLink[]>{
    return of(QUESTIONNAIRE_LINK_LIST_MOCK);
  }




}
