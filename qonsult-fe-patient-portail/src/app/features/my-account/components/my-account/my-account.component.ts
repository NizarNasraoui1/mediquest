import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/shared/models/user';
import { MyAccountService } from '../../services/my-account.service';
import { FormBuilder } from '@angular/forms';
import { ToasterService } from 'src/app/shared/services/toast.service';

@Component({
  selector: 'app-my-account',
  templateUrl: './my-account.component.html',
  styleUrls: ['./my-account.component.scss']
})
export class MyAccountComponent implements OnInit{
    adminInformations:User;
    changePasswordForm;
    passwordsNotEquals = false;
    wrongPassword = false;

    constructor(private myAccountService:MyAccountService,private fb:FormBuilder,private toasterService:ToasterService){}

    ngOnInit(): void {
        this.initChangePasswordForm();
    }

    getAdminInformations(){
        this.myAccountService.getAdminInformations().subscribe((res)=>{
            this.adminInformations = res;
        });
    }

    initChangePasswordForm(){
        this.changePasswordForm = this.fb.group({
            oldPassword:[''],
            oldPasswordConfirmation:[''],
            newPassword:['']
        })
    }

    changePassword(){
        const formValue = this.changePasswordForm.value;
        if(formValue.oldPassword!=formValue.oldPasswordConfirmation){
            this.passwordsNotEquals= true;
            return;
        }
        else{
            this.passwordsNotEquals= false;
        }
        this.myAccountService.changePassword(formValue).subscribe({
            next: (res)=>{
                this.wrongPassword = false;
                this.toasterService.addSuccessMessage('Le mot de passe a été changé avec succèes');
            },
            error:(err)=>{
                this.wrongPassword = true;
            }
        })
    }


}
