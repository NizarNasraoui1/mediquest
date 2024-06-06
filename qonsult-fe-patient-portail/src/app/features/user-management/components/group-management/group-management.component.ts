import { Component } from '@angular/core';

@Component({
  selector: 'app-group-management',
  templateUrl: './group-management.component.html',
  styleUrls: ['./group-management.component.scss']
})
export class GroupManagementComponent {
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

    showAddGroupModal(){
        this.isCreateGroupModalVisible = true;
    }

    saveGroup(group){
        this.isCreateGroupModalVisible = false;
        if(group!==null){
            console.log("save");
        }
    }
}
