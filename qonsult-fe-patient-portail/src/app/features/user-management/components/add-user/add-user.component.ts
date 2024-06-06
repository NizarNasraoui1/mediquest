import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.scss']
})
export class AddUserComponent {
    @Input() visible: boolean = true;
    @Output() user = new EventEmitter<any>();

    showDialog() {
        this.visible = true;
    }

    closeModal(save:boolean){
        if(!save)this.user.emit(null);
        else this.user.emit("a");
    }

}
