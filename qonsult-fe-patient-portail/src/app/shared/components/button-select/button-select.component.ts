import {
    Component,
    EventEmitter,
    Input,
    OnInit,
    Output,
    forwardRef,
} from '@angular/core';
import { ControlValueAccessor, NG_VALUE_ACCESSOR } from '@angular/forms';

@Component({
    selector: 'app-button-select',
    templateUrl: './button-select.component.html',
    styleUrls: ['./button-select.component.scss'],
    providers: [
        {
            provide: NG_VALUE_ACCESSOR,
            useExisting: forwardRef(() => ButtonSelectComponent),
            multi: true,
        },
    ],
})
export class ButtonSelectComponent implements OnInit,ControlValueAccessor {
    @Input() options: any[] = [];
    @Output() onSelect: EventEmitter<string> = new EventEmitter();
    selectedOption: any;

    constructor() {}

    ngOnInit(): void {}

    onChange: any = () => {};
    onTouch: any = () => {};

    writeValue(value: any): void {
        this.selectedOption = value;
    }

    registerOnChange(fn: any): void {
        this.onChange = fn;
    }

    registerOnTouched(fn: any): void {
        this.onTouch = fn;
    }

    selectOption(option: any): void {
        let value = option.id == undefined || option.id == null? option : option.id;
        this.selectedOption = value;
        this.onChange(value);
        this.onTouch();
    }
}
