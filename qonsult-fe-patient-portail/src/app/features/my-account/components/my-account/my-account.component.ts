import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/shared/models/user';
import { MyAccountService } from '../../services/my-account.service';
import { FormBuilder, Validators } from '@angular/forms';
import { ToasterService } from 'src/app/shared/services/toast.service';

@Component({
  selector: 'app-my-account',
  templateUrl: './my-account.component.html',
  styleUrls: ['./my-account.component.scss']
})
export class MyAccountComponent implements OnInit{
    adminInformations:User;
    changePasswordForm;
    adminInformationsForm;
    passwordsNotEquals = false;
    wrongPassword = false;

    constructor(private myAccountService:MyAccountService,private fb:FormBuilder,private toasterService:ToasterService){}

    ngOnInit(): void {
        this.initChangePasswordForm();
        this.getAdminInformations();
    }

    getAdminInformations(){
        this.myAccountService.getAdminInformations().subscribe((res)=>{
            this.adminInformations = res;
            this.initAdminInforamtions(res);
        });
    }

    initAdminInforamtions(adminInformations) {
        this.adminInformationsForm = this.fb.group({
            firstName: [adminInformations.firstName,[Validators.required]],
            lastName: [adminInformations.lastName,[Validators.required]],
            email: [adminInformations.email, [Validators.required, Validators.email]],
            tel: [adminInformations.tel,[Validators.required]]
        });
    }

    initChangePasswordForm(){
        this.changePasswordForm = this.fb.group({
            oldPassword:[''],
            oldPasswordConfirmation:[''],
            newPassword:['']
        })
    }

    updateAdminInformations(){
        console.log(this.adminInformationsForm.value)
        if(!this.adminInformationsForm.valid){
            this.toasterService.addWarnMessage("Le formulaire saisi contient des erreurs");
            return;
        }
        this.myAccountService.upateAdminInformations(this.adminInformationsForm.value).subscribe((res)=>{
            this.toasterService.addSuccessMessage("Modification faite avec succèes");
        });
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
