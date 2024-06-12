import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { UserManagementService } from '../../services/user-management.service';

@Component({
  selector: 'app-roles-management',
  templateUrl: './roles-management.component.html',
  styleUrls: ['./roles-management.component.scss']
})
export class RolesManagementComponent implements OnInit {
    form;
    roles = [];
    groups = [];
    groupRoles= [];

    constructor(private fb:FormBuilder,private userManagementService:UserManagementService){}

    ngOnInit(): void {
        this.getRoles();
    }

    getRoles(){
        this.userManagementService.getRoleManagement().subscribe((res)=>{
            this.roles = res.roles;
            this.groups = res.groups;
                this.initForm();
            });

    }

    initForm(){
        this.groups.forEach((e) => {
            let selectedRoles = [...e.roles];
            let group = {
                id:e.id,
                selectedRoles:selectedRoles
            }
            this.groupRoles=[...this.groupRoles,group];
        });
    }

    save(){
        console.log(this.groupRoles);
    }
}
