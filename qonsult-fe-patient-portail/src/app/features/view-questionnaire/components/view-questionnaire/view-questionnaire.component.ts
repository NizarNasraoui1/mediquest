import { AfterViewChecked, AfterViewInit, Component, ElementRef, Input, OnInit, ViewChild, ViewEncapsulation } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ViewQuestionnaireService } from '../../services/view-questionnaire.service'
import { PdfViewerModule } from 'ng2-pdf-viewer'; // <- import PdfViewerModule
import { Binary } from '@angular/compiler';
import { QuestionTypeEnum } from 'src/app/features/dentist-questionnaire/models/question-type.enum';
import { ViewQuestionnaireModule } from '../../view-questionnaire.module';
import { SharedModule } from 'src/app/shared/shared.module';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { ActivatedRoute } from '@angular/router';

@Component({
    selector: 'app-view-questionnaire',
    standalone: true,
    imports: [CommonModule, PdfViewerModule,SharedModule,MatProgressSpinnerModule],
    templateUrl: './view-questionnaire.component.html',
    styleUrls: ['./view-questionnaire.component.scss'],
    encapsulation: ViewEncapsulation.None
})
export class ViewQuestionnaireComponent implements OnInit, AfterViewInit {
    @Input() htmlText = "<div style='border: 2px solid black; padding: 10px; margin-bottom: 10px;'><h2>1. Mon état civil</h2><label for='name' style='display: block; margin: 5px 0;'>Nom :</label><input type='text' id='name' name='name' style='width: 100%; padding: 5px;'></div><img src='data:image/png;base64,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'>";
    @Input() id: string;
    questionTypeEnum = QuestionTypeEnum;
    pdfUrl: string | null = null;
    questionnaireReponse:any;
    src:string;
    date = new Date();
    constructor(private viewQuestionnaireService: ViewQuestionnaireService,private activatedRoute:ActivatedRoute) { }

    ngAfterViewInit(): void {
        setTimeout(() => {
            this.convert();
        }, 200);
        this.generatePdf();
    }

    ngOnInit(): void {
        this.getQuestionnaireId();
    }

    getQuestionnaireId(){
        this.activatedRoute.paramMap.subscribe(params=>{
            this.id = params.get('id');
            this.getQuestionnaire();
        })
    }

    generatePdf(){
        setTimeout(() => {
            const divElement = document.querySelector('#questionnaire-pdf').outerHTML;
            this.viewQuestionnaireService.generatePdf(divElement).subscribe((res: any) => {
                this.pdfUrl = URL.createObjectURL(res);
            });
        }, 1000);
    }

    getQuestionnaire(){
        this.viewQuestionnaireService.getQuestionnaireResponse(this.id).subscribe((res)=>{
            this.questionnaireReponse=res;
        });
    }

    convert() {
        const canvas: any = document.getElementsByTagName('canvas');
        const ctx = canvas[0].getContext('2d');
        ctx.font = '30px Arial';
        let img = canvas[0].toDataURL('image/png');
        this.src = img;
      }


    printPdf(): void {
        if (this.pdfUrl) {
            const printWindow = window.open(this.pdfUrl);
        }
    }
}
