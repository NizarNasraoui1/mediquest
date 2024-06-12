import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { UserManagementService } from '../../services/user-management.service';
import { ToasterService } from 'src/app/shared/services/toast.service';

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

    constructor(private fb:FormBuilder,private userManagementService:UserManagementService,private toasterService:ToasterService){}

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
        let groupRoles = this.groupRoles.map((e)=>{
            let rolesIds = e.selectedRoles.map((e)=>e.id);
            return {
                groupId:e.id,
                rolesIds:rolesIds
            }
        });
        this.userManagementService.updateRoles(groupRoles).subscribe((e)=>{
            this.toasterService.addSuccessMessage("Vos roles sont mises à jour avec succèes");
        })
    }
}
