import { Component, EventEmitter, Input, OnInit, Output, ViewEncapsulation } from '@angular/core';

@Component({
    selector: 'app-add-user',
    templateUrl: './add-user.component.html',
    styleUrls: ['./add-user.component.scss'],
    encapsulation:ViewEncapsulation.None
})
export class AddUserComponent implements OnInit {
    @Input() visible: boolean = true;
    @Output() saveUser = new EventEmitter<any>();
    @Output() modifyUser = new EventEmitter<any>();
    @Output() closedModal = new EventEmitter<any>();
    userToUpdate: any;
    groups = [];

    constructor(){}

    ngOnInit(): void {
        this.getGroups();
    }

    showDialog() {
        this.visible = true;
    }

    getGroups(){
        this.groups = [
            { name: 'ADMIN', id: '1' },
            { name: 'MEDECIN', id: '2' },
            { name: 'AUTRE', id: '3' }
        ];
    }

    submit() {
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
