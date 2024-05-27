import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DragDropModule } from '@angular/cdk/drag-drop';
import { BrowserModule } from '@angular/platform-browser';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';

import { GoogleFormCloneMainComponent } from './google-form-clone-main/google-form-clone-main.component';
import { GoogleFormViewerComponent } from './google-form-viewer/google-form-viewer.component';
import { GoogleFormBuilderComponent } from './google-form-builder/google-form-builder.component';
import { GoogleFormCloneModelComponent } from './google-form-clone-model/google-form-clone-model.component';
import { FormQuestionComponent } from './form-question/form-question.component';
import { FormGroupDirective, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { QuestionViewerComponent } from './question-viewer/question-viewer.component';;
import { RadioButtonModule } from 'primeng/radiobutton';
import { InputTextModule } from 'primeng/inputtext';
import { DropdownModule } from 'primeng/dropdown';
import { ButtonModule } from 'primeng/button';
import { MultiSelectModule } from 'primeng/multiselect';

const COMPONENTS = [
  GoogleFormCloneMainComponent,
  GoogleFormViewerComponent,
  GoogleFormBuilderComponent,
  GoogleFormCloneModelComponent,
  FormQuestionComponent,
  QuestionViewerComponent,
];

@NgModule({
  imports: [
    CommonModule,
    DragDropModule,
    MatSlideToggleModule,
    FormsModule,
    ReactiveFormsModule,
    RadioButtonModule,
    InputTextModule,
    DropdownModule,
    ButtonModule,
    MultiSelectModule
  ],
  exports: [COMPONENTS],
  declarations: [COMPONENTS],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [FormGroupDirective]
})
export class GoogleFormCloneModule {}
