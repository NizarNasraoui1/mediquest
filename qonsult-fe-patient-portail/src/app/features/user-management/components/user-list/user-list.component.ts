import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})
export class UserListComponent implements OnInit {
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
        throw new Error('Method not implemented.');
    }


    deleteUser(id){

    }

    showAddUserModal(){
        this.isCreateUserModalVisible = true;
    }

    saveUser(user){
        this.isCreateUserModalVisible = false;
        if(user!==null){
            console.log("save");
        }
    }
}
