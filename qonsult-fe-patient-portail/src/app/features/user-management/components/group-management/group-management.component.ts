import { Component, OnInit, ViewChild } from '@angular/core';
import { AddGroupComponent } from '../add-group/add-group.component';
import { UserManagementService } from '../../services/user-management.service';
import { Group } from 'src/app/shared/models/group';

@Component({
  selector: 'app-group-management',
  templateUrl: './group-management.component.html',
  styleUrls: ['./group-management.component.scss']
})
export class GroupManagementComponent implements OnInit {
    @ViewChild('addUpdateGroupComponent')addUpdateGroupComponent:AddGroupComponent;
    isCreateGroupModalVisible = false;
    groups:Group[];

    constructor(private userManagementService:UserManagementService){}

    ngOnInit(): void {
        this.getGroups();
    }

    getGroups(){
        this.userManagementService.getGroups().subscribe((res)=>{
            this.groups = res;
        })
    }

    deleteGroup(id){

    }

    displayModal(){
        this.isCreateGroupModalVisible = true;
    }

    modifyGroup(group){
        this.displayModal();
        this.addUpdateGroupComponent.groupToUpdate = group;
    }

    addNewGroup(){
        this.addUpdateGroupComponent.groupToUpdate = null;
        this.displayModal();
    }

    saveGroup(Group){
        this.displayModal();
        if(Group!==null){
            console.log("save");
        }
    }

    onModalClosed(){
        this.isCreateGroupModalVisible = false;
    }
}
