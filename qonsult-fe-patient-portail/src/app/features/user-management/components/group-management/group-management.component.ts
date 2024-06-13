import { Component, OnInit, ViewChild } from '@angular/core';
import { AddGroupComponent } from '../add-group/add-group.component';
import { UserManagementService } from '../../services/user-management.service';
import { Group } from 'src/app/shared/models/group';
import { ToasterService } from 'src/app/shared/services/toast.service';

@Component({
  selector: 'app-group-management',
  templateUrl: './group-management.component.html',
  styleUrls: ['./group-management.component.scss']
})
export class GroupManagementComponent implements OnInit {
    @ViewChild('addUpdateGroupComponent')addUpdateGroupComponent:AddGroupComponent;
    isCreateGroupModalVisible = false;
    groups:Group[];

    constructor(private userManagementService:UserManagementService,private toasterService:ToasterService){}

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

    saveGroup(group){
        if(group!=null){
            this.userManagementService.addGroup(group).subscribe((res)=>{
                this.toasterService.addSuccessMessage("Group ajouté avec succèes");
                this.getGroups();
            });
        }
    }

    onModalClosed(){
        this.isCreateGroupModalVisible = false;
    }
}
