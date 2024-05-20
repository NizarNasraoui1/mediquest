import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { QuestionnairesHistoryComponent } from './components/questionnaires-history/questionnaires-history.component';

const routes: Routes = [
    {path:'',component:QuestionnairesHistoryComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes),QuestionnairesHistoryComponent],
  exports: [RouterModule]
})
export class QuestionnairesHistoryRoutingModule { }
