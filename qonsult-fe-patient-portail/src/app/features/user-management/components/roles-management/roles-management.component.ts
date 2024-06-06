import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-roles-management',
  templateUrl: './roles-management.component.html',
  styleUrls: ['./roles-management.component.scss']
})
export class RolesManagementComponent implements OnInit {
    form;
    roles: any[] = [
        { name: 'roleName1', id: '1' },
        { name: 'roleName2', id: '2' },
        { name: 'Production', id: '3' },
        { name: 'Research', id: '4' }
    ];
    groups=[
        {
            id:"1",
            groupId:"1",
            groupName:"groupName",
            roles:[{
                id:'1',
                name:"roleName1"
            },
            {
                id:'2',
                name:"roleName2"
            }
        ]
        }
    ];
    selectedRoles=[];

    constructor(private fb:FormBuilder){}

    ngOnInit(): void {
        this.initForm();
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
    }

    save(){
        console.log(this.selectedRoles)
    }
}
