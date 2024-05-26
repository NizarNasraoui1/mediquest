import { Component, OnInit } from '@angular/core';
import {
  FormArray,
  FormBuilder,
  FormGroup,
  FormGroupDirective,
} from '@angular/forms';

@Component({
  selector: 'google-form-clone-main',
  templateUrl: './google-form-clone-main.component.html',
  styleUrls: ['./google-form-clone-main.component.css'],
  providers: [FormGroupDirective],
})
export class GoogleFormCloneMainComponent implements OnInit {
  active = 1;
  mainForm: FormGroup;

  constructor(public fb: FormBuilder) {
    let freeTextFormGroup = this.fb.group({
      id: ['q1'],
      orderNo: [1],
      type: ['question'],
      required: [true],
      question: this.fb.group({
        text: ['What is your favourite fruit?'],
        placeholder: ['e.g Apple, Tomato, etc'],
        type: ['freeText'],
        selectedAnswers: this.fb.array([]),
        offeredAnswers: this.fb.array([]),
      }),
    });

    let titleFormGroup = this.fb.group({
      id: ['q1'],
      orderNo: [0],
      type: ['title'],
      required: [true],
      title: this.fb.group({
        text: ['AMLCFT'],
        description: ['question title'],
        type: ['title'],
      }),
    });

    let radioFormGroup = this.fb.group({
      id: ['q1'],
      orderNo: [0],
      type: ['question'],
      required: [true],
      question: this.fb.group({
        text: [''],
        type: ['singleSelection'],
        selectedAnswers: this.fb.array([]),
        offeredAnswers: this.fb.array([
          this.fb.group({
            id: ['01'],
            orderNo: [0],
            value: [''],
            remarkAnswer: [false],
            remarkAnswerValue: [''],
          }),
          this.fb.group({
            id: ['02'],
            orderNo: [1],
            value: [''],
            remarkAnswer: [false],
            remarkAnswerValue: [''],
          }),
        ]),
      }),
    });
    this.mainForm = this.fb.group({
      questionList: this.fb.array([
        titleFormGroup,
        freeTextFormGroup,
        radioFormGroup,
      ]),
    });
  }

  ngOnInit() {}
}
