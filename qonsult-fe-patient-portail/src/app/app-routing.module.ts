import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { AppLayoutComponent } from './layout/app.layout.component';
import { NotfoundComponent } from './features/notfound/notfound.component';
import { AuthGardService as AuthGard } from './core/_services/auth-gard.service';
import { HomeComponent } from './features/home/home/home.component';

@NgModule({
    imports: [
        RouterModule.forRoot([
    {
        path: '',
        component: HomeComponent,
    },
    {
        path: 'home',
        component: AppLayoutComponent,
        children: [
            {
                path: '',
                loadChildren: () => import('./features/dashboard/dashboard.module').then((m) => m.DashboardModule),
            },
            {
                path: 'my-questionnaires',
                loadChildren: () => import('./features/my-questionnaires/my-questionnaires-routing.module').then((m) => m.MyQuestionnairesRoutingModule),
            },
            {
                path: 'mailbox',
                loadChildren: () => import('./features/mailbox/mailbox-routing.module').then((m) => m.MailboxRoutingModule),
            },
            {
                path: 'history',
                loadChildren: () => import('./features/questionnaires-history/questionnaires-history-routing.module').then((m) => m.QuestionnairesHistoryRoutingModule),
            },
            {
                path: 'sending-box',
                loadChildren: () => import('./features/sending-box/sending-box-routing.module').then((m) => m.SendingBoxRoutingModule),
            },
            {
                path: 'view-questionnaire',
                loadChildren: () => import('./features/view-questionnaire/view-questionnaire-routing.module').then((m) => m.ViewQuestionnaireRoutingModule),
            }
        ],
        canActivate: [AuthGard],
    },
    {
        path: 'questionnaire',
        loadChildren: () => import('./features/dentist-questionnaire/questionnaire.module').then((m) => m.QuestionnaireModule),
    },
    {
        path: 'auth',
        loadChildren: () => import('./core/auth/auth.module').then((m) => m.AuthModule),
    },
    { path: 'pages/notfound', component: NotfoundComponent },
    { path: '**', redirectTo: 'pages/notfound' },
], {
    scrollPositionRestoration: 'enabled',
    anchorScrolling: 'enabled',
    initialNavigation: 'enabledBlocking'
}),
    ],
    exports: [RouterModule],
})
export class AppRoutingModule {}
