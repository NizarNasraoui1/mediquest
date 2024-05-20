import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ViewQuestionnaireRoutingModule } from './view-questionnaire-routing.module';
import { ViewQuestionnaireComponent } from './components/view-questionnaire/view-questionnaire.component';
import { SharedModule } from 'src/app/shared/shared.module';


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    ViewQuestionnaireRoutingModule,
    ViewQuestionnaireComponent
  ]
})
export class ViewQuestionnaireModule { }
