import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DentistQuestionnaireRoutingModule } from './questionnaire-routing.module';
import { RadioButtonModule } from 'primeng/radiobutton';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import { QuestionnaireComponent } from './components/question/questionnaire.component';
import {MatRadioModule} from '@angular/material/radio';
import {MatSliderModule} from '@angular/material/slider';
import { CheckboxModule } from 'primeng/checkbox';
import {MatInputModule} from '@angular/material/input';
import { InputTextModule } from 'primeng/inputtext';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatButtonModule} from '@angular/material/button';
import {SelectButtonModule} from 'primeng/selectbutton';
import { StepsModule } from 'primeng/steps';
import {CalendarModule} from 'primeng/calendar';
import { InputNumberModule } from 'primeng/inputnumber';
import { SharedModule } from 'src/app/shared/shared.module';
import { DialogModule } from 'primeng/dialog';
import { MatDialogModule} from '@angular/material/dialog';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { SubmitSeccessefulComponent } from './components/submit-seccesseful/submit-seccesseful.component';
import { WelcomeComponent } from './components/welcome/welcome.component';
import { InputMaskModule } from 'primeng/inputmask';
import { IConfig,NgxMaskDirective, provideNgxMask } from 'ngx-mask'
import { QuestionnaireAlreadyPassedComponent } from './components/questionnaire-already-passed/questionnaire-already-passed.component';

const maskConfig: Partial<IConfig> = {
  validation: false,
};

@NgModule({
  declarations: [
    QuestionnaireComponent,
    SubmitSeccessefulComponent,
    WelcomeComponent,
    QuestionnaireAlreadyPassedComponent
  ],
  imports: [
    CommonModule,
    DentistQuestionnaireRoutingModule,
    RadioButtonModule,
    FormsModule,
    ReactiveFormsModule,
    MatProgressBarModule,
    MatRadioModule,
    MatSliderModule,
    CheckboxModule,
    MatInputModule,
    InputTextModule,
    MatTooltipModule,
    MatButtonModule,
    SelectButtonModule,
    StepsModule,
    CalendarModule,
    InputNumberModule,
    SharedModule,
    DialogModule,
    MatDialogModule,
    MatProgressSpinnerModule,
    InputMaskModule,
    NgxMaskDirective
  ],
  exports : [QuestionnaireComponent],
  providers: [provideNgxMask()]
})
export class QuestionnaireModule { }
