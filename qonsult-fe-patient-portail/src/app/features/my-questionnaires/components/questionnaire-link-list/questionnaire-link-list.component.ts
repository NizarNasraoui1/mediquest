import { Component, NO_ERRORS_SCHEMA, OnInit } from '@angular/core';
import { QuestionnaireService } from 'src/app/features/dentist-questionnaire/service/questionnaire.service';
import { QuestionnaireLink } from '../../model/quetionnaire-link-list';
import { QuestionnaireLinkComponent } from '../questionnaire-link/questionnaire-link.component';
import { CommonModule } from '@angular/common';
import { QuestionnairesLinkService } from '../../services/questionnaires-link.service';

@Component({
  standalone: true,
  imports : [CommonModule,QuestionnaireLinkComponent],
  selector: 'app-questionnaire-link-list',
  templateUrl: './questionnaire-link-list.component.html',
  styleUrls: ['./questionnaire-link-list.component.scss'],
})
export class QuestionnaireLinkListComponent implements OnInit {
    questionnaireLinks : QuestionnaireLink[] = [];
  constructor(private questionnaireService:QuestionnaireService,private questionnaireLinkService:QuestionnairesLinkService) { }

  ngOnInit(): void {
    this.questionnaireLinkService.getMyQuestionnairesLinks().subscribe((res)=>{
        this.questionnaireLinks=res;
    });
  }

}
