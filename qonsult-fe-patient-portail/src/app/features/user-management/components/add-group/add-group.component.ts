import { Component, EventEmitter, Input, Output, ViewEncapsulation } from '@angular/core';

@Component({
  selector: 'app-add-group',
  templateUrl: './add-group.component.html',
  styleUrls: ['./add-group.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class AddGroupComponent {
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
