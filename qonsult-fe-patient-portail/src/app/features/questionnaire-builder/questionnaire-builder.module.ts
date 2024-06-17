import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { QuestionnaireBuilderRoutingModule } from './questionnaire-builder-routing.module';
import { QuestionnaireBuilderComponent } from './components/questionnaire-builder/questionnaire-builder.component';
import { GoogleFormCloneModule } from '../google-form-clone/google-form-clone.module';
import { QuestionnaireSectionComponent } from './components/questionnaire-section/questionnaire-section.component';
import { ReactiveFormsModule } from '@angular/forms';
import { InputTextModule } from 'primeng/inputtext';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';

@NgModule({
  declarations: [
    QuestionnaireBuilderComponent,
    QuestionnaireSectionComponent
  ],
  imports: [
    CommonModule,
    QuestionnaireBuilderRoutingModule,
    GoogleFormCloneModule,
    ReactiveFormsModule,
    InputTextModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    ButtonModule
  ]
})
export class QuestionnaireBuilderModule { }
