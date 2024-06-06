import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MyQuestionnairesComponent } from './components/my-questionnaires/my-questionnaires.component';

const routes: Routes = [
    {path: '',component: MyQuestionnairesComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MyQuestionnairesRoutingModule { }
