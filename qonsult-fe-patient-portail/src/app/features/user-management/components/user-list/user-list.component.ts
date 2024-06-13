import { Component, OnInit, ViewChild } from '@angular/core';
import { AddUserComponent } from '../add-user/add-user.component';
import { UserManagementService } from '../../services/user-management.service';
import { Observable } from 'rxjs';
import { User } from 'src/app/shared/models/user';
import { ToasterService } from 'src/app/shared/services/toast.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})
export class UserListComponent implements OnInit {
    @ViewChild("addUpdateUserComponent") addUpdateUserComponent:AddUserComponent;
    isCreateUserModalVisible = false;
    users:User[];

    constructor(private userManagementService:UserManagementService,private toasterService:ToasterService){}

    ngOnInit(): void {
        this.getUsers();
    }

    getUsers(){
        this.userManagementService.getUsers().subscribe((res)=>{
            this.users = res;
        })
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
            this.userManagementService.addUser(user,user.group.id).subscribe({
                next:(res)=>{
                    this.toasterService.addSuccessMessage("l'utilisateur est ajouté avec succèes");
                    this.getUsers();
                },
                error:(err)=>{
                    this.toasterService.addWarnMessage("le nom d'utilisateur est déja pris");
                }
        })
    }
}

    updateUser(user){
        this.displayModal();
        if(user!==null){
            this.userManagementService.updateUser(user.id,user).subscribe({
                next:(res)=>{
                    this.toasterService.addSuccessMessage("l'utilisateur est mis à jour avec succèes");
                    this.getUsers();
                },
                error:(err)=>{
                    this.toasterService.addWarnMessage("le nom d'utilisateur est déja pris");
                }
        })
    }
    }

    onModalClosed(){
        this.isCreateUserModalVisible = false;
    }
}
