import {
    Component,
    ElementRef,
    OnDestroy,
    OnInit,
    Renderer2,
    ViewChild,
    ViewEncapsulation,
} from '@angular/core';
import { AutoCompleteModule } from 'primeng/autocomplete';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DateAdapter, MatNativeDateModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatCardModule } from '@angular/material/card';
import { QuestionnaireModule } from 'src/app/features/dentist-questionnaire/questionnaire.module';
import { SendingBoxService } from '../../sending-box.service';
import { CommonModule } from '@angular/common';
import { format } from 'date-fns';
import { ButtonModule } from 'primeng/button';
import { ToasterService } from 'src/app/shared/services/toast.service';
import { SubSink } from 'subsink';
import { DropdownModule } from 'primeng/dropdown';

interface AutoCompleteCompleteEvent {
    originalEvent: Event;
    query: string;
}

@Component({
    selector: 'app-sending-box',
    standalone: true,
    imports: [
        CommonModule,
        AutoCompleteModule,
        FormsModule,
        MatNativeDateModule,
        MatDatepickerModule,
        MatCardModule,
        QuestionnaireModule,
        ReactiveFormsModule,
        ButtonModule,
        DropdownModule
    ],
    templateUrl: './sending-box.component.html',
    styleUrls: ['./sending-box.component.scss'],
    encapsulation: ViewEncapsulation.None,
})
export class SendingBoxComponent implements OnInit, OnDestroy {
    selected: Date | null;
    selectedItems: any[] | undefined = [];
    questionnaireNames: any[] | undefined = [];
    filteredQuestionnaires: any[] | undefined = [];
    selectedQuestionnaire: any;
    form:FormGroup;;
    subs = new SubSink();
    emails = [];

    constructor(
        private dateAdapter: DateAdapter<Date>,
        private sendingBoxService: SendingBoxService,
        private formBuilder: FormBuilder,
        private toasterService: ToasterService,
        private elementRef: ElementRef
    ) {
        this.dateAdapter.setLocale('fr');
    }

    ngOnInit(): void {
        this.initForm();
        this.getQuestionnairesNames();
    }

    initForm() {
        this.form = this.formBuilder.group({
            emails: '',
            appointmentDate: '',
            questionnaire: '',
        });
    }

    search(event: AutoCompleteCompleteEvent) {
        this.emails = [event.query];
    }

    handleKeyPress() {
        const element =
            this.elementRef.nativeElement.querySelector('li[role="option"]');
        element.click();
    }

    getQuestionnairesNames() {
        this.sendingBoxService.getQuestionnaireNames().subscribe((res) => {
            this.questionnaireNames = res;
        });
    }

    updateFormDate(value: any) {
        this.form.get('appointmentDate').setValue(format(value, 'dd/MM/yyyy'));
    }

    filterQuestionnaireNames(event: AutoCompleteCompleteEvent) {
        let filtered: any[] = [];
        let query = event.query;
        for (let i = 0; i < (this.questionnaireNames as any[]).length; i++) {
            let item = (this.questionnaireNames as any[])[i];
            if (item.name.toLowerCase().indexOf(query.toLowerCase()) == 0) {
                filtered.push(item);
            }
        }
        this.filteredQuestionnaires = filtered;
    }

    checkMails(mails: string[]) {
        const emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,8}$/;
        for(let mail of mails){
            if (!emailRegex.test(mail)) {
                return mail;
            }
        }
        return '';
    }

    sendInvitation() {
        const notValidMail = this.checkMails(this.form.value.emails);
        if(notValidMail!=''){
            this.toasterService.addWarnMessage(`Veuillez vérifier le format de cette adresse mail ${notValidMail}`);
            return;
        }
        if(this.form.value.emails==''){
            this.toasterService.addWarnMessage('Veuillez ajouter au moins une adresse mail');
            return;
        }
        if(this.form.value.questionnaire==''){
            this.toasterService.addWarnMessage('Veuillez selectionner un questionnaire');
            return;
        }
        if(this.form.value.appointmentDate==''){
            this.toasterService.addWarnMessage('Veuillez choisir une date de rendez vous');
            return;
        }
        this.subs.sink = this.sendingBoxService
            .sendQuestionnaireInvitation(this.form.value)
            .subscribe(() => {
                this.toasterService.addSuccessMessage('Invitations envoyées');
                setTimeout(() => {
                    window.location.reload();
                }, 2000);
            });
    }

    ngOnDestroy(): void {
        this.subs.unsubscribe();
    }
}
