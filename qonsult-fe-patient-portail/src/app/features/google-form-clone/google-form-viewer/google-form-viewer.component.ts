import { CdkDragDrop, moveItemInArray } from '@angular/cdk/drag-drop';
import { Component, HostListener, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'google-form-clone-viewer',
  templateUrl: './google-form-viewer.component.html',
  styleUrls: ['./google-form-viewer.component.scss'],
  // schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class GoogleFormViewerComponent implements OnInit {
  @Input() mainForm: FormGroup;
  numberOfClicks = 0;

  constructor() {}

  ngOnInit() {
  }

  freeTextQuestion: any = {
    id: 'q1',
    orderNo: 1,
    type: 'question',
    required: true,
    question: {
      text: 'What is your favourite fruit?',
      placeholder: 'e.g. Apple, Tomato, etc',
      type: 'freeText',
    },
  };

  radioQuestion = {
    id: 'q2',
    orderNo: 2,
    type: 'question',
    required: false,
    question: {
      text: '',
      type: 'radio',
      offeredAnswers: [
        {
          id: '01',
          orderNo: 1,
          value: 'first Answer',
          remarkAnswer: true,
          remarkAnswerValue: 'idk',
        },
        {
          id: '02',
          orderNo: 2,
          value: 'second Answer',
          remarkAnswer: false,
          remarkAnswerValue: 'idk',
        },
        {
          id: '02',
          orderNo: 2,
          value: 'other',
          remarkAnswer: false,
          remarkAnswerValue: 'idk',
        },
      ],
    },
  };

  checkboxQuestion = {
    id: 'q3',
    orderNo: 3,
    type: 'question',
    required: false,
    question: {
      text: 'Checkbox',
      type: 'checkbox',
      offeredAnswers: [
        {
          id: '01',
          orderNo: 1,
          value: 'first checkbox Answer',
          remarkAnswer: true,
          remarkAnswerValue: 'idk',
        },
        {
          id: '02',
          orderNo: 2,
          value: 'second checkbox Answer',
          remarkAnswer: true,
          remarkAnswerValue: 'idk',
        },
        {
          id: '03',
          orderNo: 2,
          value: 'second checkbox Answer',
          remarkAnswer: false,
          remarkAnswerValue: 'idk',
        },
      ],
    },
  };

  cardElements = [
    this.freeTextQuestion,
    this.radioQuestion,
    this.checkboxQuestion,
  ];

  dropRadioAnswer(event: CdkDragDrop<string[]>) {
    moveItemInArray(
      this.radioQuestion.question.offeredAnswers,
      event.previousIndex,
      event.currentIndex
    );
  }

  dropCardElement(event: CdkDragDrop<string[]>) {
    moveItemInArray(this.cardElements, event.previousIndex, event.currentIndex);
  }
}
