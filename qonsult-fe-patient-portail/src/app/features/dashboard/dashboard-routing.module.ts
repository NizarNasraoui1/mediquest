import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { DashboardComponent } from './dashboard.component';
import { AuthGardService as AuthGard } from 'src/app/core/_services/auth-gard.service';
@NgModule({
    imports: [
        RouterModule.forChild([{ path: '', component: DashboardComponent }]),
    ],
    exports: [RouterModule],
})
export class DashboardsRoutingModule {}
