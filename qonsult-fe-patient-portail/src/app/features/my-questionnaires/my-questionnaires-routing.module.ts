import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MyQuestionnairesComponent } from './components/my-questionnaires/my-questionnaires.component';
import { FavoritesComponent } from './components/favorites/favorites.component';

const routes: Routes = [
    {path: '',component: MyQuestionnairesComponent},
    {path: 'favourites',component: FavoritesComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MyQuestionnairesRoutingModule { }
