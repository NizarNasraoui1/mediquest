import { Component, OnInit, ViewChild } from '@angular/core';
import { AddUserComponent } from '../add-user/add-user.component';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})
export class UserListComponent implements OnInit {
    @ViewChild("addUser") addUserComponent:AddUserComponent;
    isCreateUserModalVisible = false;
    users=[
        {
            id:"1",
            userName:"userName",
            email:"email",
            groupName:"groupName",
            groupId:"groupId",
            createdDate:"createdDate"
        }
    ]

    constructor(){}
    ngOnInit(): void {
    }


    deleteUser(id){

    }

    modifyUser(user){
        console.log("here");
        this.showAddUserModal();
        this.addUserComponent.userToUpdate = user;
    }

    showAddUserModal(){
        this.isCreateUserModalVisible = true;
    }

    addUser(){
        this.addUserComponent.userToUpdate = null;
        this.showAddUserModal();
    }

    saveUser(user){
        this.isCreateUserModalVisible = false;
        if(user!==null){
            console.log("save");
        }
    }
}
