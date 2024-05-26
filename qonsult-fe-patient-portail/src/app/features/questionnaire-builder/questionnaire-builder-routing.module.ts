import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { QuestionnaireBuilderComponent } from './components/questionnaire-builder/questionnaire-builder.component';

const routes: Routes = [
    {path:'',component:QuestionnaireBuilderComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class QuestionnaireBuilderRoutingModule { }
