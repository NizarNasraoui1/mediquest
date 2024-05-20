import {
    Component,
    HostListener,
    Input,
    OnInit,
    Optional,
} from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { Signature } from '../../models/signature';

export enum ComponentModeEnum {
    EDIT,
    VIEW,
}
@Component({
    selector: 'app-draw',
    templateUrl: './draw.component.html',
    styleUrls: ['./draw.component.scss'],
})
export class DrawComponent implements OnInit {
    @Input() set signature(value: any) {
        var signature = value.signature;
        if(!signature) signature=value;
        this.clickX = signature.clickX;
        this.clickY = signature.clickY;
        this.clickDrag = signature.clickDrag;
        this.mode = ComponentModeEnum.VIEW;
    }

    _canvas: HTMLCanvasElement;
    context: CanvasRenderingContext2D;
    paint: boolean;
    clickX: number[] = [];
    clickY: number[] = [];
    clickDrag: boolean[] = [];
    mode: ComponentModeEnum = ComponentModeEnum.EDIT;
    enumExposed = ComponentModeEnum;

    @HostListener('document:mousedown', ['$event'])
    pressMouseEventHandler(event: MouseEvent) {
        this.pressEventHandler(event);
    }

    @HostListener('document:touchstart', ['$event'])
    pressTouchEventHandler(event: TouchEvent) {
        this.pressEventHandler(event);
    }

    @HostListener('document:mousemove', ['$event'])
    mouseDragEventHandler(event: MouseEvent) {
        this.dragEventHandler(event);
    }

    @HostListener('document:touchmove', ['$event'])
    touchDragEventHandler(event: TouchEvent) {
        this.dragEventHandler(event);
    }

    @HostListener('document:mouseup', ['$event'])
    mouseReleaseEventHandler(event: MouseEvent) {
        this.releaseEventHandler();
    }

    @HostListener('document:touchend', ['$event'])
    touchReleaseEventHandler(event: TouchEvent) {
        this.releaseEventHandler();
    }

    @HostListener('document:mouseout', ['$event'])
    cancelMouseEventHandler() {
        this.cancelEventHandler();
    }

    @HostListener('document:touchcancel', ['$event'])
    cancelTouchEventHandler() {
        this.cancelEventHandler();
    }

    constructor(@Optional() public dialogRef: MatDialogRef<DrawComponent>) {}

    ngOnInit(): void {}

    doAction() {
        const signature = {
            clickX: this.clickX,
            clickY: this.clickY,
            clickDrag: this.clickDrag,
        };
        this.dialogRef.close({ signature });
    }

    closeDialog() {
        this.dialogRef.close({ event: 'Cancel' });
    }





    ngAfterViewInit() {
        let _canvas = document.getElementById('canvas') as HTMLCanvasElement;
        let context = _canvas.getContext('2d');
        context!!.lineCap = 'round';
        context!!.lineJoin = 'round';
        context!!.strokeStyle = 'black';
        context!!.lineWidth = 1;

        this._canvas = _canvas;
        this.context = context!!;

        this.redraw();
        this.createUserEvents();
    }

    private createUserEvents() {
        let _canvas = this._canvas;

        document
            .getElementById('clear')!!
            .addEventListener('click', this.clearEventHandler);
    }

    private redraw() {
        let clickX = this.clickX;
        let context = this.context;
        let clickDrag = this.clickDrag;
        let clickY = this.clickY;
        for (let i = 0; i < clickX.length; ++i) {
            context.beginPath();
            if (clickDrag[i] && i) {
                context.moveTo(clickX[i - 1], clickY[i - 1]);
            } else {
                context.moveTo(clickX[i] - 1, clickY[i]);
            }

            context.lineTo(clickX[i], clickY[i]);
            context.stroke();
        }
        context.closePath();
    }

    private addClick(x: number, y: number, dragging: boolean) {
        this.clickX.push(x);
        this.clickY.push(y);
        this.clickDrag.push(dragging);
    }

    private clearCanvas() {
        this.context.clearRect(0, 0, this._canvas.width, this._canvas.height);
        this.clickX = [];
        this.clickY = [];
        this.clickDrag = [];
    }

    private clearEventHandler = () => {
        this.clearCanvas();
    };

    private releaseEventHandler = () => {
        this.paint = false;
        this.redraw();
    };

    private cancelEventHandler = () => {
        this.paint = false;
    };

    private pressEventHandler(e: MouseEvent | TouchEvent) {
        let rect = this._canvas.getBoundingClientRect();
        let mouseX = (e as TouchEvent).changedTouches
            ? (e as TouchEvent).changedTouches[0].clientX
            : (e as MouseEvent).clientX;
        let mouseY = (e as TouchEvent).changedTouches
            ? (e as TouchEvent).changedTouches[0].clientY
            : (e as MouseEvent).clientY;
        mouseX -= rect.left;
        mouseY -= rect.top;

        this.paint = true;
        this.addClick(mouseX, mouseY, false);
        this.redraw();
    }

    private dragEventHandler(e: MouseEvent | TouchEvent) {
        let rect = this._canvas.getBoundingClientRect();
        let mouseX = (e as TouchEvent).changedTouches
            ? (e as TouchEvent).changedTouches[0].clientX
            : (e as MouseEvent).clientX;
        let mouseY = (e as TouchEvent).changedTouches
            ? (e as TouchEvent).changedTouches[0].clientY
            : (e as MouseEvent).clientY;
        mouseX -= rect.left;
        mouseY -= rect.top;

        if (this.paint) {
            this.addClick(mouseX, mouseY, true);
            this.redraw();
        }

        //e.preventDefault();
    }
}
