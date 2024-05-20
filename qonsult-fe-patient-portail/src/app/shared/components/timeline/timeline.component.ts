import { AfterViewInit, Component, Input, OnInit } from '@angular/core';
import * as d3 from 'd3';

const red = '#c21616';
const yellow = '#ecc442';
const blue = '#0858ad';
const green = '#5eb41f';

@Component({
    selector: 'app-timeline',
    templateUrl: './timeline.component.html',
    styleUrls: ['./timeline.component.scss'],
})
export class TimelineComponent implements OnInit, AfterViewInit {
    @Input() data: any;
    timeline: any;
    popup: any;

    constructor() {}

    ngOnInit(): void {}

    ngAfterViewInit(): void {
        this.timeline = d3.select('#timeline');
        this.timeline.style('width', this.calculate_FullWidth(this.data));
        // Create event circles
        this.timeline
            .selectAll('.event-date-pointer')
            .data(this.data)
            .enter()
            .append('div')
            .attr('class', 'event-date-pointer')
            .style('filter', (d: any) =>
                d.state == 'Undone'
                    ? 'opacity(0.4) contrast(0.4)'
                    : 'brightness(1)',
            )
            .style(
                'left',
                (d: any, i: number) =>
                    this.calculate_EventPosition(i, true) + 'px',
            )
            .append('div')
            .text((d: any, i: number) => this.checkDateUnicity(d, i, this.data))
            .style('color', (d: any) =>
                d.state == 'Current' ? 'green' : 'black',
            )
            .style('background-color', (d: any) =>
                d.state == 'Current' ? '#40ff0030' : '',
            );

        this.timeline
            .selectAll('.event-label-pointer')
            .data(this.data)
            .enter()
            .filter((d: any) => d.label && d.label != '')
            .append('div')
            .attr('class', 'event-label-pointer')
            .style('filter', (d: any) =>
                d.state == 'Undone'
                    ? 'opacity(0.4) contrast(0.4)'
                    : 'brightness(1)',
            )
            .style('filter', (d: any) =>
                d.state == 'Undone'
                    ? 'opacity(0.4) contrast(0.4)'
                    : 'brightness(1)',
            )
            .style(
                'left',
                (d: any) => this.calculate_LPPosition(d, this.data) + 'px',
            )
            .style(
                'height',
                (d: any, i: number) => this.calculate_LPHeight(d, i) + 'px',
            )
            .style(
                'top',
                (d: any, i: number) => this.calculate_LPTop(d, i) + 'px',
            )
            .append('div')
            .attr('class', 'pointer-circle')
            .style('--key-color', (d: any) => d.color)
            .append('div')
            .attr('class', 'pointer-text')
            .text((d: any) => d.label)
            .style('--key-color', (d: any) => d.color + '80')
            .style('top', (d: any) => this.calculate_LPTextPosition(d) + 'px');

        this.timeline
            .selectAll('.event')
            .data(this.data)
            .enter()
            .append('div')
            .on('mouseover', this.showPopup)
            .on('mouseout', this.hidePopup)
            .attr('class', (d: any) =>
                d.state == 'Current' ? 'event-current event' : 'event',
            )
            .style('filter', (d: any) =>
                d.state == 'Undone'
                    ? 'opacity(0.4) contrast(0.4) '
                    : 'brightness(1)',
            )
            .style(
                'left',
                (d: any, i: number) => this.calculate_EventPosition(i) + 'px',
            )
            .style('background-color', (d: any) => d.color);
    }

    calculate_EventPosition(pos: number, isPointer?: boolean) {
        const maxPos = this.data.length;
        const timelineWidth = this.timeline.node().offsetWidth;
        return (pos / maxPos) * timelineWidth + (isPointer ? 20 : 0);
    }

    calculate_LPPosition(d: any, data: any[]) {
        const idx = data.indexOf(d);
        return idx * 45 + 20;
    }

    calculate_LPHeight(d: any, uid: number) {
        return uid % 2 ? 70 : 20;
    }

    calculate_LPTop(d: any, uid: number) {
        return uid % 2 ? 70 : 120;
    }

    calculate_FullWidth(data: any[]) {
        //should take into consideration the width of the entire screen
        return 45 * data.length + 'px';
    }

    calculate_LPTextPosition(d: any) {
        return (
            -12 -
            (d.label.length / 17 > Math.floor(d.label.length / 17)
                ? Math.floor(d.label.length / 17) + 1
                : Math.floor(d.label.length / 17)) *
                19
        );
    }

    checkDateUnicity(d: any, i: number, data: any[]) {
        let firstOcc = data.find((elem) => elem.date == d.date);
        let idx = data.indexOf(firstOcc);
        if (idx == i || d.state == 'Current') {
            const dateObj = new Date(d.date);
            const day = dateObj.getDate();
            const month = dateObj.getMonth() + 1;
            const year = dateObj.getFullYear();
            const formattedDate = `${day > 9 ? day : '0' + day}/${
                month > 9 ? month : '0' + month
            } \n ${year}`;
            return formattedDate;
        }
        return '';
    }

    // Function to show the popup on hover
    showPopup(event: Event, d: any) {
        this.popup = document.getElementById('popup');
        this.popup.attributeStyleMap.set('--key-color', d.color);
        this.popup.innerHTML = ``;
        this.popup.style.filter = d.state == 'Undone' ? 'grayscale(0.8)' : '';
        const header = document.createElement('div');
        header.classList.add('popup-header');
        this.popup.attributeStyleMap.set('--key-color', d.color);
        this.popup.attributeStyleMap.set(
            '--status-color',
            d.state == 'Done'
                ? '#40ff07'
                : d.state == 'Undone'
                ? '#b2acac'
                : '#ffce6d',
        );
        this.popup.appendChild(header);

        if (d.label != '' && d.label) {
            const label = document.createElement('div');
            label.innerText = d.label;
            label.classList.add('popup-label');
            this.popup.attributeStyleMap.set('--key-color', d.color);
            this.popup.appendChild(label);
        }

        const desc = document.createElement('div');
        desc.innerText = d.description;
        desc.classList.add('popup-description');
        this.popup.appendChild(desc);

        const date = document.createElement('div');
        const dateObj = new Date(d.date);
        const currDateObj = new Date();
        date.innerText = d.date;
        date.classList.add('popup-date');
        date.style.backgroundColor =
            d.state == 'Done'
                ? '#e4e4e4'
                : dateObj > currDateObj
                ? '#afeeaf'
                : '#ffb5b5';
        this.popup.appendChild(date);

        const rect = (event.target! as any).getBoundingClientRect();
        this.popup.style.left = rect.x - 55 + 'px';
        this.popup.style.top = rect.y + 35 + 'px';
        this.popup.style.display = 'block';
        this.popup.style.borderColor = d.color;
    }

    hidePopup() {
        /* const popup = document.getElementById("popup");
    popup.style.display = "none"; */
    }
}
