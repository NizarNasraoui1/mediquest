import { Component, OnInit, ViewChild } from '@angular/core';
import { AddUserComponent } from '../add-user/add-user.component';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})
export class UserListComponent implements OnInit {
    @ViewChild("addUpdateUserComponent") addUpdateUserComponent:AddUserComponent;
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

    displayModal(){
        this.isCreateUserModalVisible = true;
    }

    modifyUser(user){
        this.displayModal();
        this.addUpdateUserComponent.userToUpdate = user;
    }

    addNewUser(){
        this.addUpdateUserComponent.userToUpdate = null;
        this.displayModal();
    }

    saveUser(user){
        this.displayModal();
        if(user!==null){
            console.log("save");
        }
    }

    onModalClosed(){
        this.isCreateUserModalVisible = false;
    }
}
