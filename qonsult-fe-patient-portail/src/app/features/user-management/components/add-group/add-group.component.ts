import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-add-group',
  templateUrl: './add-group.component.html',
  styleUrls: ['./add-group.component.scss']
})
export class AddGroupComponent implements OnInit,OnChanges {
    @Input() visible: boolean = true;
    @Output() saveGroup = new EventEmitter<any>();
    @Output() modifyGroup = new EventEmitter<any>();
    @Output() closedModal = new EventEmitter<any>();
    groupToUpdate: any;
    title ='';
    groupName: string;

    ngOnInit(): void {
    }

    ngOnChanges(changes: SimpleChanges): void {
        this.setTitle();
    }

    setTitle(){
        if(this.groupToUpdate!=null){
            this.title = "Modifier";
            this.groupName = this.groupToUpdate.name;
        }
        else{
            this.title = "Créer";
            this.groupName = '';
        }
        this.title = this.title + " le groupe";
    }

    showDialog() {
        this.visible = true;
    }

    submit() {
        if (this.groupToUpdate != null) {
            this.modifyGroup.emit(this.groupToUpdate);
        } else {
            this.saveGroup.emit(this.groupName);
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
