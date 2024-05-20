import { OnInit, Component, OnDestroy, ViewEncapsulation, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TableModule } from 'primeng/table';
import { TagModule } from 'primeng/tag';
import { DropdownModule } from 'primeng/dropdown';
import { MultiSelectModule } from 'primeng/multiselect';
import { InputTextModule } from 'primeng/inputtext';
import { TabViewModule } from 'primeng/tabview';
import { InputMaskModule } from 'primeng/inputmask';
import { FormBuilder } from '@angular/forms';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { QuestionnaireHistoryService } from '../../services/questionnaire-history.service';
import { SearchQuestionnaire } from '../../model/search-questionnaire.model';
import { PaginatorModule } from 'primeng/paginator';
import { debounceTime } from 'rxjs';
import { SubSink } from 'subsink';
import { RouterModule } from '@angular/router';
@Component({
    selector: 'app-questionnaires-history',
    standalone: true,
    imports: [
        CommonModule,
        TableModule,
        TagModule,
        DropdownModule,
        MultiSelectModule,
        InputTextModule,
        TabViewModule,
        InputMaskModule,
        FormsModule,
        ReactiveFormsModule,
        PaginatorModule,
        RouterModule
    ],
    templateUrl: './questionnaires-history.component.html',
    styleUrls: ['./questionnaires-history.component.css'],
    encapsulation: ViewEncapsulation.None,
})
export class QuestionnairesHistoryComponent implements OnInit, OnDestroy {

    questionnaires!: any[];

    representatives!: any[];

    statuses!: any[];

    loading: boolean = false;

    activityValues: number[] = [0, 100];

    form;

    currentPage = 0;

    pageSize = 5;

    totalRecords = 0;

    subs = new SubSink();

    constructor(private formBuilder: FormBuilder,private questionnaireHistoryService:QuestionnaireHistoryService) {}

    ngOnInit() {
        this.initForm();
        this.subscribeToFormValueChanges();
        this.searchQuestionnaires();
    }

    initForm(){
        this.form = this.formBuilder.group({
            firstName: '',
            lastName: '',
            appointmentDate: '',
            birthdate:'',
            status: '',
            passed: '',
        });
    }


    isFormDatesValid():boolean{
        return this.isValidDate(this.form.value.birthdate) && this.isValidDate(this.form.value.appointmentDate);
    }

    isValidDate(dateString: string): boolean {
        if(dateString=='' || dateString == 'JJ/MM/AAAA') return true;
        const regex = /^(0[1-9]|[12][0-9]|3[01])\/(0[1-9]|1[012])\/(19|20)\d\d$/;
        return regex.test(dateString);
    }

    subscribeToFormValueChanges(){
        this.form.valueChanges.pipe(debounceTime(1000)).subscribe(()=>{
            if(this.isFormDatesValid()){
                this.searchQuestionnaires();
            }
        })
    }

    searchQuestionnaires(){
        const formValue= this.form.value;
        const searchQuestionnaireRequest: SearchQuestionnaire = {
            firstName: formValue.firstName,
            lastName: formValue.lastName,
            birthdate: formValue.birthdate=='JJ/MM/AAAA'?null:formValue.birthdate,
            appointmentDate: formValue.appointmentDate=='JJ/MM/AAAA'?null:formValue.appointmentDate
        }
        this.subs.sink = this.questionnaireHistoryService.searchQuestionnaires(this.currentPage,this.pageSize,searchQuestionnaireRequest).subscribe((res)=>{
            this.questionnaires = res.content;
            this.totalRecords = res.totalElements;
        })
    }

    onPageChange(event: any) {
        this.currentPage = event.first;
        this.pageSize = event.rows;
        this.searchQuestionnaires();
    }

    ngOnDestroy() {
        this.subs.unsubscribe();
    }
}
