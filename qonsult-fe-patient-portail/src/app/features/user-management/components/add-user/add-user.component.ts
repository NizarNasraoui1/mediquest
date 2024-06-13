import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges, ViewEncapsulation } from '@angular/core';
import { UserManagementService } from '../../services/user-management.service';
import { FormBuilder, Validators } from '@angular/forms';

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
    form;

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
        }
        this.title = this.title + " l'utilisateur";
    }

    initForm(){
        this.form = this.fb.group({
            firstName:['',[Validators.required]],
            lastName:['',[Validators.required]],
            username:['',[Validators.required]],
            email:['',[Validators.required,Validators.email]],
            tel:['',[Validators.required]],
        });
    }

    populateForm(user){
        this.form.setValue({
            firstName: user.firstName || '',
            lastName: user.lastName || '',
            username: user.username || '',
            email: user.email || '',
            tel: user.tel || '',
        });
    }

    showDialog() {
        this.visible = true;
    }

    getGroups(){
        this.userManagementService.getGroups().subscribe((res)=>{
            this.groups = res;
        })
    }

    submit() {
        console.log(this.form.value);
        if (this.userToUpdate != null) {
            this.saveUser.emit("user");
        } else {
            this.modifyUser.emit("updated user");
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
