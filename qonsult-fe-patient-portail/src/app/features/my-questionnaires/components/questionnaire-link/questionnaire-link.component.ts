import { Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { QRCodeModule } from 'angularx-qrcode';
import { CardModule } from 'primeng/card';
import { ButtonModule } from 'primeng/button';
import { NgxPrintModule } from 'ngx-print';
import { QuestionnaireLink } from '../../model/quetionnaire-link-list';

@Component({
    selector: 'app-questionnaire-link',
    standalone: true,
    imports: [CommonModule, QRCodeModule, CardModule, ButtonModule, NgxPrintModule
    ],
    templateUrl: './questionnaire-link.component.html',
    styleUrls: ['./questionnaire-link.component.scss'],
})
export class QuestionnaireLinkComponent implements OnInit {

    @Input() questionnaireLink: QuestionnaireLink;
    @ViewChild('printSection') printSection: ElementRef;
    src: string;

    constructor() { }

    ngOnInit(): void {
        setTimeout(() => {
            this.convert();
        }, 1000);
    }

    copyLinkToClipboard() {
        const link = this.questionnaireLink.url;
        navigator.clipboard.writeText(link).then(() => {
        }).catch(err => {
            console.error('Could not copy text: ', err);
        });
    }

    convertCanvasToImage(containerClass: string): string {
        const container = document.querySelector(`.${containerClass}`);
        const canvas = container.querySelector('canvas') as HTMLCanvasElement;
        return canvas.toDataURL('image/png');
    }

    print() {
        const canvasImageUrl = this.convertCanvasToImage('qrcode');
        var printContents = document.getElementById("print-section").innerHTML;
        printContents = printContents.replace(/<canvas.*?<\/canvas>/, `<img src="${canvasImageUrl}" style="height: 250px; width: 250px;">`);
        const originalContents = document.body.innerHTML;
        document.body.innerHTML = printContents;
        window.print();
        document.body.innerHTML = originalContents;
    }

    convert() {
        const canvas: any = document.getElementsByTagName('canvas');
        const ctx = canvas[0].getContext('2d');
        ctx.font = '30px Arial';
        const img = canvas[0].toDataURL('image/png');
        this.src = img;
    }
}
