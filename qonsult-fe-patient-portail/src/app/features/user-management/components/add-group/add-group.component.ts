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

    ngOnInit(): void {
    }

    ngOnChanges(changes: SimpleChanges): void {
        this.setTitle();
    }

    setTitle(){
        if(this.groupToUpdate!=null){
            this.title = "Modifier";
        }
        else{
            this.title = "Créer";
        }
        this.title = this.title + " le groupe";
    }

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
