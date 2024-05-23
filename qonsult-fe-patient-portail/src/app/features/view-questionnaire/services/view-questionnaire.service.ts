import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpUtilService } from 'src/app/util/service/http-util.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { QUESTIONNAIRE_RESOPNSE_MOCK } from '../components/view-questionnaire/questionnaire.mock';

const CONVERT_TO_PDF_URL = '/api/mediquest/pdf/generate';
const QUESTIONNAIRE_ANSWER_URL= '/api/mediquest/view-questionnaire';
const headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Accept': 'application/pdf'
  });
@Injectable({
  providedIn: 'root'
})
export class ViewQuestionnaireService {

  constructor(private httpUtilService:HttpUtilService,private http:HttpClient) { }

  generatePdf(htmlTemplate:string):Observable<Blob>{
    return this.http.post(CONVERT_TO_PDF_URL, htmlTemplate,{
        headers: headers,
        responseType: 'blob'
      });
  }

  getQuestionnaireResponse(id:string):Observable<any>{
    return this.httpUtilService.get(`${QUESTIONNAIRE_ANSWER_URL}/${id}`)
    // return of(QUESTIONNAIRE_RESOPNSE_MOCK);
  }


}
