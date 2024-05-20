import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ViewQuestionnaireComponent } from './components/view-questionnaire/view-questionnaire.component';

const routes: Routes = [
    {path:':id',component:ViewQuestionnaireComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ViewQuestionnaireRoutingModule { }
