import { CdkDragDrop, moveItemInArray } from '@angular/cdk/drag-drop';
import { Component, EventEmitter, HostListener, Input, OnInit, Output } from '@angular/core';
import {_} from 'lodash';
import {
  FormArray,
  FormBuilder,
  FormGroup,
  FormGroupDirective,
} from '@angular/forms';
import { ToasterService } from 'src/app/shared/services/toast.service';


@Component({
  selector: 'google-form-builder',
  templateUrl: './google-form-builder.component.html',
  styleUrls: ['./google-form-builder.component.css'],
})
export class GoogleFormBuilderComponent implements OnInit {
  @Input() mainForm!: FormGroup;
  @Output() formChanged = new EventEmitter();
  @Output() onSaveButtonClicked = new EventEmitter();

  newTextFormGroup = this.fb.group({
    id: [0],
    orderNo: [0],
    type: ['question'],
    required: [true],
    question: this.fb.group({
      text: [''],
      type: ['singleSelection'],
      selectedAnswers: [''],
      conditions: this.fb.array([]),
      offeredAnswers: this.fb.array([
        this.fb.group({
          id: [0],
          orderNo: [0],
          value: [''],
          remarkAnswer: [false],
          remarkAnswerValue: [''],
        }),
        this.fb.group({
          id: [2],
          orderNo: [1],
          value: [''],
          remarkAnswer: [false],
          remarkAnswerValue: [''],
        }),
      ]),
    }),
  });

  constructor(public fb: FormBuilder, private toasterService: ToasterService) {
    let radioFormGroup = this.fb.group({
      id: [0],
      orderNo: [0],
      type: ['question'],
      required: [true],
      question: this.fb.group({
        text: [''],
        type: ['singleSelection'],
        selectedAnswers: [''],
        conditions: this.fb.array([]),
        offeredAnswers: this.fb.array([
          this.fb.group({
            id: [0],
            orderNo: [0],
            value: [''],
            remarkAnswer: [false],
            remarkAnswerValue: [''],
          }),
          this.fb.group({
            id: [2],
            orderNo: [1],
            value: [''],
            remarkAnswer: [false],
            remarkAnswerValue: [''],
          }),
        ]),
      }),
    });
    this.mainForm = this.fb.group({
      questionList: this.fb.array([radioFormGroup]),
    });
  }

  ngOnInit() {
    this.formatForm();
  }

  formatForm() {
    this.mainForm.valueChanges.subscribe((form) => {
      this.formChanged.emit(this.transformQuestionsFormat(form.questionList));
    });
  }

  transformQuestionsFormat(questions) {
    return questions.map(question => {
      let form = {
        text: question.question.text,
        type: question.question.type,
        selectedAnswers: question.question.selectedAnswers,
        offeredAnswers: question.question.offeredAnswers.map(answer => answer.value),
        conditions: question.question.conditions.map((e)=>e.orderNo)
      };
      return form;
    });
  }

  findValueByOrderId(array, orderNo) {
    if (!array || orderNo == "" || orderNo.length == 0) return [];
    let result = [];
    orderNo.forEach((e) => {
      result = [...result, ...array.filter(element => element.orderNo == e)?.map((e) => e.value)];
    });
    return result;
  }

  dropCardElement(event: CdkDragDrop<string[]>) {
    moveItemInArray(this.questionListArray, event.previousIndex, event.currentIndex);
    this.updateOrderNo();
  }

  async addNewQuestion() {
    let questionList = this.mainForm.get('questionList') as FormArray;
    let i = questionList.length;
    questionList.insert(i + 1, this.fb.group({
      id: [i + 1],
      orderNo: [0],
      type: ['question'],
      required: [true],
      question: this.fb.group({
        text: [''],
        type: ['singleSelection'],
        selectedAnswers: [''],
        conditions: this.fb.array([]),
        offeredAnswers: this.fb.array([
          this.fb.group({
            id: [0],
            orderNo: [0],
            value: [''],
            remarkAnswer: [false],
            remarkAnswerValue: [''],
          }),
          this.fb.group({
            id: [1],
            orderNo: [1],
            value: [''],
            remarkAnswer: [false],
            remarkAnswerValue: [''],
          }),
        ]),
      }),
    }));

    await this.updateOrderNo();
  }

  duplicateQuestion(i: number) {
    let targetQ = this.questionList.at(i) as FormGroup;
    let newFormGroup: FormGroup = _.cloneDeep(targetQ);
    this.questionList.insert(i + 1, newFormGroup);
    this.updateOrderNo();
  }

  deleteQuestion(i: number) {
    this.questionList.removeAt(i);
    this.updateOrderNo();
  }

  async updateOrderNo() {
    let questionList = this.mainForm.get('questionList')['controls'];
    await questionList.forEach((question, index) => {
      question.patchValue({
        orderNo: index,
      });
    });
  }

  get questionListArray() {
    return this.mainForm.get('questionList')['controls'];
  }

  get questionList() {
    return this.mainForm.get('questionList') as FormArray;
  }

  checkForm(): boolean {
    const form = this.mainForm.value.questionList;

    for (let e of form) {
      let question = e.question;
      if (question.text == "") {
        this.toasterService.addWarnMessage('Une question doit avoir un titre');
        return false;
      }
      if (question.offeredAnswers.length == 0) {
        this.toasterService.addWarnMessage('une question doit avoir au moins un choix à sélectionner');
        return false;
      }
      if (question.selectedAnswers == "") {
        this.toasterService.addWarnMessage('une question doit avoir au moins une réponse correcte selectionné');
        return false;
      }

      for (let option of question.offeredAnswers) {
        if (option.value == "") {
          this.toasterService.addWarnMessage('une option doit avoir un texte');
          return false;
        }
      }
    }
    return true;
  }

  onSave() {
    if (this.checkForm()) {
      this.onSaveButtonClicked.emit();
    }
  }
}
