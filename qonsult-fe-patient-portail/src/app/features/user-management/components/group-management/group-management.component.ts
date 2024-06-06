import { Component, ViewChild } from '@angular/core';
import { AddGroupComponent } from '../add-group/add-group.component';

@Component({
  selector: 'app-group-management',
  templateUrl: './group-management.component.html',
  styleUrls: ['./group-management.component.scss']
})
export class GroupManagementComponent {
    @ViewChild('addUpdateGroupComponent')addUpdateGroupComponent:AddGroupComponent;
    isCreateGroupModalVisible = false;
    groups=[
        {
            id:"1",
            groupName:"groupName",
            groupId:"groupId",
            createdDate:"createdDate"
        }
    ]

    deleteGroup(id){

    }

    displayModal(){
        this.isCreateGroupModalVisible = true;
    }

    modifyGroup(Group){
        this.displayModal();
        this.addUpdateGroupComponent.groupToUpdate = Group;
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
