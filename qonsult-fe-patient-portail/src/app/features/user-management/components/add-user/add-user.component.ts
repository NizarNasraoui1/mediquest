import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
    selector: 'app-add-user',
    templateUrl: './add-user.component.html',
    styleUrls: ['./add-user.component.scss'],
})
export class AddUserComponent {
    @Input() visible: boolean = true;
    @Output() saveUser = new EventEmitter<any>();
    @Output() modifyUser = new EventEmitter<any>();
    userToUpdate: any;

    showDialog() {
        this.visible = true;
    }

    closeModal(save: boolean) {
        if (this.userToUpdate != null) {
            if (!save) this.saveUser.emit(null);
            else this.saveUser.emit(null);
        } else {
            if (!save) this.modifyUser.emit(null);
            else this.modifyUser.emit(null);
        }
    }

    onModalClose() {
        this.closeModal(null);
    }
}
