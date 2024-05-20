import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { QuestionnaireComponent } from './components/question/questionnaire.component';

const routes: Routes = [
    {path: ':schema/:id', component: QuestionnaireComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DentistQuestionnaireRoutingModule { }
