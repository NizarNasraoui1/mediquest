import { Component, Input, Output, EventEmitter, OnInit, ViewEncapsulation, NgZone } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray, FormControl } from '@angular/forms';
import { Question } from '../../models/question';
import { QuestionnaireService } from '../../service/questionnaire.service';
import { QuestionTypeEnum } from '../../models/question-type.enum';
import { ElementRef } from '@angular/core';
import { registerLocaleData } from '@angular/common';
import localeFr from '@angular/common/locales/fr';
import { PrimeNGConfig } from 'primeng/api';
import { Questionnaire } from '../../models/questionnaire';
import { DrawComponent } from 'src/app/shared/components/draw/draw.component';
import { MatDialog } from '@angular/material/dialog';
import { Signature } from 'src/app/shared/models/signature';
import { ActivatedRoute, Router } from '@angular/router';
import { ToasterService } from 'src/app/shared/services/toast.service';

registerLocaleData(localeFr, 'fr');

@Component({
    selector: 'app-question',
    templateUrl: './questionnaire.component.html',
    styleUrls: ['./questionnaire.component.css'],
    encapsulation: ViewEncapsulation.None
})
export class QuestionnaireComponent implements OnInit {
    @Input() preview = false;
    @Input()  id:string;
    questionnaire: Questionnaire;
    generalInformtionsForm: FormGroup;
    questionForm!: FormGroup;
    certificationForm!: FormGroup;
    slicedQuestionnaireArray: Question[];
    incrementNumber: number = 5;
    pageIndex = 0;
    questionTypeEnum = QuestionTypeEnum;
    topicIndex: number = 0;
    topicNames: string[] = [];
    topicQuestionsMap: Map<string, Question[]> = new Map();
    displayModal = true;
    signature: Signature;
    appointmentDate: any;
    isSubmitted: boolean = false;

    constructor(private fb: FormBuilder, private questionnaireService: QuestionnaireService, private elRef: ElementRef,
        private primengConfig: PrimeNGConfig, public dialog: MatDialog, private zone: NgZone,private activatedRoute:ActivatedRoute,
        private toasterService:ToasterService) {
        this.configureCalendarLanguage();

    }

    ngOnInit(): void {
        if(!this.preview){
            this.activatedRoute.paramMap.subscribe((params)=>{
                this.id= params.get('id');
                this.getQuestionnaireAndInitParams()
            });
        }
        else{
            this.getQuestionnaireAndInitParams()
        }
    }

    getQuestionnaireAndInitParams(){
        this.questionnaireService.getQuestions(this.id).subscribe((res) => {
            this.questionnaire = res;
            //this.sortQuestions();
            this.initForms();
            this.generateTopicQuestionsMap();
            this.initializeQuestionnaireSubArray();
        });
    }

    ontopicIndexChange(event: number) {
        this.topicIndex = event;
    }

    openSignatureModal() {
        this.scrollToTopBar();

        this.dialog.open(DrawComponent, {
            width: '400px',
            height: '25%'
        }).afterClosed().subscribe((data: any) => {
            this.signature = data;
            setTimeout(() => {
                this.certificationForm.get('signature')?.setValue(this.getSignatureData());
                console.log(this.certificationForm.get('signature'))
            }, 1000);
        });
    }

    getSignatureData(){
        const canvas: HTMLCanvasElement = document.getElementById('canvas') as HTMLCanvasElement;
        return canvas.toDataURL('image/png');
      }

    get questions(): Question[] {
        let questions = [];
        this.questionnaire.topics.forEach((topic)=>{
            questions = [...questions,...topic.questions];
        });
        return questions;
    }

    getControl(id: string) {
        return this.questionForm.controls[id] as FormControl;
    }

    initForms() {
        this.initGeneralInformaitonsForm();
        this.initQuestionsForm();
        this.initCertificationForm();
    }

    initQuestionsForm() {
        this.questionForm = this.fb.group({});
        this.questions.forEach((question) => {
            this.questionForm.addControl(question.id, this.fb.control(''));
        });
    }

    initGeneralInformaitonsForm() {
        this.generalInformtionsForm = this.fb.group({
            firstName: new FormControl(''),
            lastName: new FormControl(''),
            birthday: new FormControl(''),
            address: new FormControl(''),
            tel: new FormControl(''),
            mail: new FormControl(''),
            profession: new FormControl('')
        })
    }

    initCertificationForm() {
        this.certificationForm = this.fb.group({
            certification: new FormControl(''),
            filledBy: new FormControl(''),
            signature: new FormControl('')
        })
    }

    generateTopicQuestionsMap() {
        this.topicQuestionsMap.set('Bienvenue', []);
        this.topicQuestionsMap.set('Informations Générales', []);
        this.questionnaire.topics.forEach(topic => {
            this.topicQuestionsMap.set(topic.name,topic.questions);
        });
        this.topicNames = Array.from(this.topicQuestionsMap.keys());
        this.topicNames = [...this.topicNames, "Certification"];

    }

    onNext() {
        this.scrollToTopBar();
        this.topicIndex += 1;
        this.slicedQuestionnaireArray = [];
        this.slicedQuestionnaireArray = this.topicQuestionsMap.get(this.topicNames[this.topicIndex])!;

    }

    onPrevious() {
        this.topicIndex -= 1;
        this.slicedQuestionnaireArray = this.topicQuestionsMap.get(this.topicNames[this.topicIndex])!;
        this.scrollToTopBar();
    }

    scrollToTopBar() {
        let top = document.getElementById('questionnaire-container');
        top!!.scrollIntoView();
    }

    initializeQuestionnaireSubArray() {
        this.slicedQuestionnaireArray = this.topicQuestionsMap.get(this.topicNames[0])!;
    }

    verifyCondition(id: string): boolean {
        // const question = this.slicedQuestionnaireArray.filter(q => q.id == id)[0];
        // const conditions:Condition[] = question.conditions!;
        // if(conditions && conditions.length>0){
        //     for(let condition of conditions){
        //         let rank = condition.rank;
        //         let content = condition.content;
        //         let id = this.slicedQuestionnaireArray.filter(e=>e.rank==rank)[0].id;
        //         let control = this.questionForm.get(id);
        //         if(control){
        //             if(control.value!=content) return false;
        //         }
        //     }
        // }
        return true;
    }

    configureCalendarLanguage() {
        this.primengConfig.setTranslation({
            dayNames: ['dimanche', 'lundi', 'mardi', 'mercredi', 'jeudi', 'vendredi', 'samedi'],
            dayNamesShort: ['dim', 'lun', 'mar', 'mer', 'jeu', 'ven', 'sam'],
            dayNamesMin: ['D', 'L', 'M', 'M', 'J', 'V', 'S'],
            monthNames: ['janvier', 'février', 'mars', 'avril', 'mai', 'juin', 'juillet', 'août', 'septembre', 'octobre', 'novembre', 'décembre'],
            monthNamesShort: ['jan', 'fév', 'mar', 'avr', 'mai', 'jun', 'jul', 'aoû', 'sep', 'oct', 'nov', 'déc'],
            today: 'Aujourd\'hui',
            clear: 'Effacer'
        });
    }
    get progressBarValue() {
        return (this.topicIndex / (this.topicQuestionsMap.size)) * 100;
    }

    get isPaginationEnd() {
        return this.topicIndex == this.topicQuestionsMap.size;
    }

    get isFirstPage() {
        return this.topicIndex == 0;
    }

    submit() {
        if(!this.preview){
            this.questionnaireService.saveQuestionnaire(this.questionnaire, this.generalInformtionsForm.value, this.questionForm.value, this.certificationForm.value, this.appointmentDate)
            .subscribe((res) => {
                this.isSubmitted = true;
            });
        }
        else{
            this.toasterService.addWarnMessage("Vous ne pouvez pas soumettre ce questionnaire car vous êtes en mode visualisation uniquement");
            this.isSubmitted = true;
        }
    }

}
