import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';

const cardsColors = ["#5dd15d", "#d15daf", "#d1cd5d", "#5da6d1", "#ffbf00", "#00ecff", "#ff006a"]

@Component({
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.scss'],
})
export class DashboardComponent implements OnInit, OnDestroy {
    DashboardCards: any[] = [
        { label: 'Questionnaires du Jour', value: '709 patients' },
        { label: 'Plan de traitements', value: '650 plans' },
        { label: 'Jalons', value: '152 jalons' },
        { label: 'Soins en attente', value: '7 patients' },
        { label: 'PEC en attente', value: '12 patients' }
    ]
    name: string = '';
    fields = {
        title: 'Plan de traitement',
        arrayFields: ['nom', 'patient', 'date', 'description'],
    };
    data: any;
    options: any;

    constructor(private router: Router) { }

    ngOnInit() {
        this.configLineChart();
    }

    configLineChart(){
        const documentStyle = getComputedStyle(document.documentElement);
        const textColor = documentStyle.getPropertyValue('--text-color');
        const textColorSecondary = documentStyle.getPropertyValue('--text-color-secondary');
        const surfaceBorder = documentStyle.getPropertyValue('--surface-border');

        this.data = {
            labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
            datasets: [
                {
                    label: 'First Dataset',
                    data: [65, 59, 80, 81, 56, 55, 40],
                    fill: false,
                    borderColor: documentStyle.getPropertyValue('--blue-500'),
                    tension: 0.4
                },
                {
                    label: 'Second Dataset',
                    data: [28, 48, 40, 19, 86, 27, 90],
                    fill: false,
                    borderColor: documentStyle.getPropertyValue('--pink-500'),
                    tension: 0.4
                }
            ]
        };

        this.options = {
            maintainAspectRatio: false,
            aspectRatio: 0.6,
            plugins: {
                legend: {
                    labels: {
                        color: textColor
                    }
                }
            },
            scales: {
                x: {
                    ticks: {
                        color: textColorSecondary
                    },
                    grid: {
                        color: surfaceBorder,
                        drawBorder: false
                    }
                },
                y: {
                    ticks: {
                        color: textColorSecondary
                    },
                    grid: {
                        color: surfaceBorder,
                        drawBorder: false
                    }
                }
            }
        };
    }



    getCardColorLoop(pos: number) {
        return cardsColors[pos % cardsColors.length]
    }

    ngOnDestroy(): void {
    }
}
