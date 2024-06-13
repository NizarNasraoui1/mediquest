import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges, ViewEncapsulation } from '@angular/core';
import { UserManagementService } from '../../services/user-management.service';
import { FormBuilder, Validators } from '@angular/forms';
import { Group } from 'src/app/shared/models/group';

@Component({
    selector: 'app-add-user',
    templateUrl: './add-user.component.html',
    styleUrls: ['./add-user.component.scss'],
    encapsulation:ViewEncapsulation.None
})
export class AddUserComponent implements OnInit,OnChanges {
    @Input() visible: boolean = true;
    @Output() saveUser = new EventEmitter<any>();
    @Output() modifyUser = new EventEmitter<any>();
    @Output() closedModal = new EventEmitter<any>();
    userToUpdate: any;
    groups = [];
    title ='';
    addUpdateUserForm;
    selectedGroup:Group;
    displayAddGroupWarnMsg = false;

    constructor(private fb:FormBuilder, private userManagementService:UserManagementService){}

    ngOnInit(): void {
        this.getGroups();
        this.initForm();
    }

    ngOnChanges(changes: SimpleChanges): void {
        this.setTitle();
    }

    setTitle(){
        if(this.userToUpdate!=null){
            this.title = "Modifier";
            this.populateForm(this.userToUpdate);
        }
        else{
            this.title = "Créer";
            this.initForm();
            this.selectedGroup = null;
        }
        this.title = this.title + " l'utilisateur";
    }

    initForm(){
        this.addUpdateUserForm = this.fb.group({
            firstName:['',[Validators.required]],
            lastName:['',[Validators.required]],
            username:['',[Validators.required]],
            email:['',[Validators.required,Validators.email]],
            tel:['',[Validators.required]],
        });
    }

    populateForm(user){
        this.addUpdateUserForm.setValue({
            firstName: user.firstName || '',
            lastName: user.lastName || '',
            username: user.username || '',
            email: user.email || '',
            tel: user.tel || '',
        });
        this.selectedGroup = user.group;
    }

    showDialog() {
        this.visible = true;
    }

    getGroups(){
        this.userManagementService.getGroups().subscribe((res)=>{
            this.groups = res;
        });
    }

    submit() {
        let isFormInvalid = false;
        if(this.selectedGroup==null){
            this.displayAddGroupWarnMsg = true;
            isFormInvalid = true;
        }
        else{
            this.displayAddGroupWarnMsg = false;
        }
        if(this.addUpdateUserForm.invalid){
            this.addUpdateUserForm.markAllAsTouched();
            isFormInvalid = true;
        }
        if(isFormInvalid) return;
        let user = {
            groupId:this.selectedGroup.id,
            ...this.addUpdateUserForm.value
        }
        if (this.userToUpdate != null) {
            this.modifyUser.emit(user);
        } else {
            this.saveUser.emit(user);
        }
        this.onModalClose();
    }

    cancel(){
        this.onModalClose();
    }

    onModalClose() {
        this.closedModal.emit();
    }
}
