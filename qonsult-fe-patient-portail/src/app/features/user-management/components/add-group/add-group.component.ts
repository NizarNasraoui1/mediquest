import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-add-group',
  templateUrl: './add-group.component.html',
  styleUrls: ['./add-group.component.scss']
})
export class AddGroupComponent {
    @Input() visible: boolean = true;
    @Output() saveGroup = new EventEmitter<any>();
    @Output() modifyGroup = new EventEmitter<any>();
    @Output() closedModal = new EventEmitter<any>();
    groupToUpdate: any;

    showDialog() {
        this.visible = true;
    }

    submit() {
        if (this.groupToUpdate != null) {
            this.saveGroup.emit("Group");
        } else {
            this.modifyGroup.emit("updated Group");
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
