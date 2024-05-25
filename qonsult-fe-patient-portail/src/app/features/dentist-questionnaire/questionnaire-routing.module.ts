import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { QuestionnaireComponent } from './components/question/questionnaire.component';
import { QuestionnaireAlreadyPassedComponent } from './components/questionnaire-already-passed/questionnaire-already-passed.component';

const routes: Routes = [
    {path: ':schema/:id', component: QuestionnaireComponent},
    {path:'questionnaire-already-passed',component: QuestionnaireAlreadyPassedComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DentistQuestionnaireRoutingModule { }
