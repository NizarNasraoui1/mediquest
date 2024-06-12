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
    // roles: any[] = [
    //     { name: 'roleName1', id: '1' },
    //     { name: 'roleName2', id: '2' },
    //     { name: 'Production', id: '3' },
    //     { name: 'Research', id: '4' }
    // ];
    // groups=[
    //     {
    //         id:"1",
    //         groupId:"1",
    //         groupName:"groupName",
    //         roles:[{
    //             id:'1',
    //             name:"roleName1"
    //         },
    //         {
    //             id:'2',
    //             name:"roleName2"
    //         }
    //     ]
    //     }
    // ];
    selectedRoles=[];

    constructor(private fb:FormBuilder,private userManagementService:UserManagementService){}

    ngOnInit(): void {
        this.getRoles();
    }

    getRoles(){
        this.userManagementService.getRoleManagement().subscribe((res)=>{
            this.roles = res.roles;
            this.groups = res.groups;
            // for(let i=0;i<this.groups.length;i++){
            //     this.selectedRoles[i] = this.roles.map((e)=>{
            //         e["selected"]= false;
            //         return e;
            //     });
            //     //this.selectedRoles = [...this.selectedRoles,this.roles];
            // }
            console.log(this.selectedRoles);
                // group.roles.forEach((role)=>{

                // })
                this.initForm();
            })

    }

    initForm(){
        this.groups.forEach((e) => {
            let alreadySelectedRoles = [...e.roles];
            let group = {
                id:e.id,
                alreadySelectedRoles:alreadySelectedRoles
            }
            this.selectedRoles=[...this.selectedRoles,group];
        });
        console.log(this.selectedRoles);
    }

    save(){
        console.log(this.selectedRoles)
    }
}
