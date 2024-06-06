import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserManagementRoutingModule } from './user-management-routing.module';
import { UserManagementComponent } from './components/user-management/user-management.component';
import { RolesManagementComponent } from './components/roles-management/roles-management.component';
import { UserListComponent } from './components/user-list/user-list.component';
import { TabViewModule } from 'primeng/tabview';
import { GroupManagementComponent } from './components/group-management/group-management.component';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { CheckboxModule } from 'primeng/checkbox';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    UserManagementComponent,
    RolesManagementComponent,
    UserListComponent,
    GroupManagementComponent
  ],
  imports: [
    CommonModule,
    UserManagementRoutingModule,
    TabViewModule,
    TableModule,
    ButtonModule,
    CheckboxModule,
    ReactiveFormsModule,
    FormsModule
  ]
})
export class UserManagementModule { }
