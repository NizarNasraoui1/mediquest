import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SendingBoxComponent } from './components/sending-box/sending-box.component';

const routes: Routes = [
    {path: '',component:SendingBoxComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SendingBoxRoutingModule { }
