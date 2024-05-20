import { Injectable } from '@angular/core';
import { HttpUtilService } from 'src/app/util/service/http-util.service';
import { SearchQuestionnaire } from '../model/search-questionnaire.model';
import { PageResponse } from 'src/app/shared/models/page-response';
import { Observable } from 'rxjs';

const QUESTIONNAIRE_HISTORY_API_URL = '/api/historic/questionnaires';

@Injectable({
  providedIn: 'root'
})
export class QuestionnaireHistoryService {

  constructor(private httpUtil : HttpUtilService) { }

  searchQuestionnaires(page:number,pageSize:number,searchQuestionnaireRequest:SearchQuestionnaire):Observable<PageResponse<SearchQuestionnaire>>{
    return this.httpUtil.post(QUESTIONNAIRE_HISTORY_API_URL,searchQuestionnaireRequest,{page:page,pageSize:pageSize});
  }


}
