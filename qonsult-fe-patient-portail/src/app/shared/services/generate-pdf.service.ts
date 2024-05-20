import { Injectable } from '@angular/core';
import { HttpUtilService } from 'src/app/util/service/http-util.service';

const CONVERT_TO_PDF_URL = '/api/pdf/generate';

@Injectable({
  providedIn: 'root'
})
export class GeneratePdfService {

  constructor(private httpUtilService:HttpUtilService) { }

  generatePdf(content:string){
    this.httpUtilService.post(CONVERT_TO_PDF_URL,content);
  }
}
