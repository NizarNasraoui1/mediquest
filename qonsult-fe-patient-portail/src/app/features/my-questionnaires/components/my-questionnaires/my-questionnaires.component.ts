import { Component, OnInit } from '@angular/core';
import { ButtonModule } from 'primeng/button';
import { QuestionnaireLinkListComponent } from '../questionnaire-link-list/questionnaire-link-list.component';
@Component({
    standalone: true,
    selector: 'app-my-questionnaires',
    imports: [ButtonModule,QuestionnaireLinkListComponent],
    templateUrl: './my-questionnaires.component.html',
    styleUrls: ['./my-questionnaires.component.scss']
})
export class MyQuestionnairesComponent implements OnInit {

    constructor() { }

    ngOnInit(): void {
    }

}
