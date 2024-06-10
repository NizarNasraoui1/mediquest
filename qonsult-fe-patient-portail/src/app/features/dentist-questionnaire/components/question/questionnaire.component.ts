import {
    Component,
    Input,
    Output,
    EventEmitter,
    OnInit,
    ViewEncapsulation,
    NgZone,
    OnChanges,
    SimpleChanges,
} from '@angular/core';
import {
    FormGroup,
    FormBuilder,
    Validators,
    FormArray,
    FormControl,
} from '@angular/forms';
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
import { Condition } from '../../models/condition';
import { CodeLabel } from 'src/app/shared/models/code-label';
import { ArraysUtil } from 'src/app/util/arrays-util';
import { NumbersUtil } from 'src/app/util/numbers-util';

registerLocaleData(localeFr, 'fr');

@Component({
    selector: 'app-question',
    templateUrl: './questionnaire.component.html',
    styleUrls: ['./questionnaire.component.css'],
    encapsulation: ViewEncapsulation.None,
})
export class QuestionnaireComponent implements OnInit, OnChanges {
    @Input() preview = false;
    @Input() id: string;
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
    appointmentDate: any;
    isSubmitted: boolean = false;
    signature: Signature;
    signatureData: string;
    questionnaireAlreadyPassed = false;

    constructor(
        private fb: FormBuilder,
        private questionnaireService: QuestionnaireService,
        private elRef: ElementRef,
        private primengConfig: PrimeNGConfig,
        public dialog: MatDialog,
        private zone: NgZone,
        private activatedRoute: ActivatedRoute,
        private toasterService: ToasterService,
        private router:Router
    ) {
        this.configureCalendarLanguage();
    }

    ngOnInit(): void {
        if (!this.preview) {
            this.activatedRoute.paramMap.subscribe((params) => {
                this.id = params.get('id');
                this.getQuestionnaireAndInitParams();
            });
        } else {
            this.getQuestionnaireAndInitParams();
        }
    }

    ngOnChanges(changes: SimpleChanges): void {
        this.getQuestionnaireAndInitParams();
    }

    getQuestionnaireAndInitParams() {
        this.questionnaireService.getQuestions(this.id).subscribe({
            next: (res) => {
            this.questionnaire = res;
            this.initForms();
            this.generateTopicQuestionsMap();
            this.initializeQuestionnaireSubArray();
            },
            error: ()=>{
                this.questionnaireAlreadyPassed = true;
            }
        });
    }

    ontopicIndexChange(event: number) {
        this.topicIndex = event;
    }

    openSignatureModal() {
        this.scrollToTopBar();

        this.dialog
            .open(DrawComponent, {
                width: '400px',
                height: '25%',
            })
            .afterClosed()
            .subscribe((data: any) => {
                this.signature = data;
            });
    }

    getSignatureData() {
        const canvas: HTMLCanvasElement = document.getElementById(
            'canvas',
        ) as HTMLCanvasElement;
        return canvas.toDataURL('image/png');
    }

    get questions(): Question[] {
        let questions = [];
        this.questionnaire.topics.forEach((topic) => {
            questions = [...questions, ...topic.questions];
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
            profession: new FormControl(''),
        });
    }

    initCertificationForm() {
        this.certificationForm = this.fb.group({
            certification: new FormControl(''),
            filledBy: new FormControl(''),
            signature: new FormControl(''),
        });
    }

    generateTopicQuestionsMap() {
        this.topicQuestionsMap.set('Bienvenue', []);
        this.topicQuestionsMap.set('Informations Générales', []);
        this.questionnaire.topics.forEach((topic) => {
            this.topicQuestionsMap.set(topic.name, topic.questions);
        });
        this.topicNames = Array.from(this.topicQuestionsMap.keys());
        this.topicNames = [...this.topicNames, 'Certification'];
    }

    onNext() {
        this.scrollToTopBar();
        this.topicIndex += 1;
        this.slicedQuestionnaireArray = [];
        this.slicedQuestionnaireArray = this.topicQuestionsMap.get(
            this.topicNames[this.topicIndex],
        )!;
    }

    onPrevious() {
        this.topicIndex -= 1;
        this.slicedQuestionnaireArray = this.topicQuestionsMap.get(
            this.topicNames[this.topicIndex],
        )!;
        this.scrollToTopBar();
    }

    scrollToTopBar() {
        let top = document.getElementById('root');
        top!!.scrollIntoView();
    }

    initializeQuestionnaireSubArray() {
        this.slicedQuestionnaireArray = this.topicQuestionsMap.get(
            this.topicNames[0],
        )!;
    }

    verifyCondition(id: string): boolean {
        const question = this.slicedQuestionnaireArray.filter(q => q.id == NumbersUtil.subtractFromStringNumber(id,1))[0];
        if(question){
            const condition = question.conditions.map(e=>e.rank);
            if(condition.length==0){
                return true;
            }
            const content = question.content;
            var lastQuestionValue = this.questionForm.value[NumbersUtil.subtractFromStringNumber(id,1)];
            if(!Array.isArray(lastQuestionValue)){
                lastQuestionValue = [lastQuestionValue];
            }
            if(lastQuestionValue.length>0){
                const ranks = this.getRanksByIds(content,lastQuestionValue);
                if(!ArraysUtil.areArraysEqual(ranks,condition)){
                    return false;
                }
            }
        }
        return true;
    }

    getRanksByIds(items: CodeLabel[], ids: string[]): number[] {
    const idToRankMap = new Map<string, number>();
    items.forEach((item, index) => {
        idToRankMap.set(item.id, index);
    });
    return ids.map(id => {
        const rank = idToRankMap.get(id);
        return rank !== undefined ? rank : -1;
    });
    }

    configureCalendarLanguage() {
        this.primengConfig.setTranslation({
            dayNames: [
                'dimanche',
                'lundi',
                'mardi',
                'mercredi',
                'jeudi',
                'vendredi',
                'samedi',
            ],
            dayNamesShort: ['dim', 'lun', 'mar', 'mer', 'jeu', 'ven', 'sam'],
            dayNamesMin: ['D', 'L', 'M', 'M', 'J', 'V', 'S'],
            monthNames: [
                'janvier',
                'février',
                'mars',
                'avril',
                'mai',
                'juin',
                'juillet',
                'août',
                'septembre',
                'octobre',
                'novembre',
                'décembre',
            ],
            monthNamesShort: [
                'jan',
                'fév',
                'mar',
                'avr',
                'mai',
                'jun',
                'jul',
                'aoû',
                'sep',
                'oct',
                'nov',
                'déc',
            ],
            today: "Aujourd'hui",
            clear: 'Effacer',
        });
    }
    get progressBarValue() {
        return (this.topicIndex / this.topicQuestionsMap.size) * 100;
    }

    get isPaginationEnd() {
        return this.topicIndex == this.topicQuestionsMap.size;
    }

    get isFirstPage() {
        return this.topicIndex == 0;
    }

    submit() {
        if (!this.preview) {
            this.questionnaireService
                .saveQuestionnaire(
                    this.id,
                    this.questionnaire,
                    this.generalInformtionsForm.value,
                    this.questionForm.value,
                    this.getSignatureData(),
                    this.appointmentDate,
                )
                .subscribe((res) => {
                    this.isSubmitted = true;
                });
        } else {
            this.toasterService.addWarnMessage(
                'Vous ne pouvez pas soumettre ce questionnaire car vous êtes en mode visualisation uniquement',
            );
            this.isSubmitted = true;
        }
    }
}
