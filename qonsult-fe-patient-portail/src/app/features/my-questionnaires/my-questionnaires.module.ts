import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MyQuestionnairesRoutingModule } from './my-questionnaires-routing.module';
import { MyQuestionnairesComponent } from './components/my-questionnaires/my-questionnaires.component';
import { ButtonModule } from 'primeng/button';
import { QuestionnaireLinkComponent } from './components/questionnaire-link/questionnaire-link.component';
import { QuestionnaireLinkListComponent } from './components/questionnaire-link-list/questionnaire-link-list.component';
@NgModule({
  declarations: [
  ],
  imports: [
    MyQuestionnairesComponent,
    ButtonModule,
    CommonModule,
    MyQuestionnairesRoutingModule,
    QuestionnaireLinkComponent,
    QuestionnaireLinkListComponent

  ]
})
export class MyQuestionnairesModule { }
