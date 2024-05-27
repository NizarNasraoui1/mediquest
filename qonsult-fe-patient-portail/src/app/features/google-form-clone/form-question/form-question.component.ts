import { CdkDragDrop, moveItemInArray } from '@angular/cdk/drag-drop';
import { ChangeDetectorRef, Component, Input, OnInit, ViewEncapsulation } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, FormGroupDirective } from '@angular/forms';
import { _ } from 'lodash';

@Component({
  selector: 'form-question',
  templateUrl: './form-question.component.html',
  styleUrls: ['./form-question.component.css'],
})
export class FormQuestionComponent implements OnInit {
  @Input() questionFormGroup!: FormGroup;
  @Input() questionIndex: number;
  selectedQuestionType: string;
  isConditionSectionVisible = false;
  selectedConditions = [];

  questionsTypes = [
    { name: 'Choix unique', value: 'singleSelection' },
    { name: 'Choix multiple', value: 'multipleSelection' },
    { name: 'Texte', value: 'text' }
  ];

  mainForm: FormGroup;

  constructor(public rootFormGroup: FormGroupDirective, public fb: FormBuilder, private cdRef: ChangeDetectorRef) {}

  newTextFormGroup() {
    return this.fb.group({
      id: ['q1'],
      orderNo: [0],
      type: ['question'],
      required: [false],
      question: this.fb.group({
        text: [''],
        placeholder: [''],
        type: ['freeText'],
        selectedAnswers: this.fb.array([]),
        offeredAnswers: this.fb.array([]),
        conditions: this.fb.array([]), // Added conditions control
      }),
    });
  }

  get newSingleSelection() {
    return this.fb.group({
      id: ['q1'],
      orderNo: [0],
      type: ['question'],
      required: [true],
      question: this.fb.group({
        text: [''],
        placeholder: [''],
        type: ['singleSelection'],
        conditions: this.fb.array([]), // Added conditions control
        selectedAnswers: this.fb.array([]),
        offeredAnswers: this.fb.array([
          this.fb.group({
            id: ['01'],
            orderNo: [1],
            value: [''],
            remarkAnswer: [true],
            remarkAnswerValue: ['idk'],
          }),
          this.fb.group({
            id: ['02'],
            orderNo: [2],
            value: ['No'],
            remarkAnswer: [false],
            remarkAnswerValue: ['idk'],
          }),
        ]),
      }),
    });
  }

  ngOnInit() {
    this.mainForm = this.rootFormGroup.control;
  }

  onChangeQuestionType(i: number) {
    let currentQuestion = this.questionList.at(i) as FormGroup;
    const selectedType = this.questionFormGroup.get('question.type').value;
    const orderNo = this.questionFormGroup.get('orderNo').value;

    if (selectedType === 'freeText') {
      currentQuestion.setValue(this.newTextFormGroup());
      currentQuestion.patchValue({ orderNo: orderNo });
    } else if (selectedType === 'singleSelection' || selectedType === 'multipleSelection') {
      currentQuestion.patchValue({
        orderNo: orderNo,
        question: { type: selectedType },
      });

      let selectedAnswers = currentQuestion.get('question.selectedAnswers') as FormArray;
      selectedAnswers.patchValue([]);

      let offeredAnswers = currentQuestion.get('question.offeredAnswers') as FormArray;
      while (offeredAnswers.length !== 0) {
        offeredAnswers.removeAt(0);
      }
      this.addNewRadioOption();
      this.addNewRadioOption();
    }
  }

  dropRadioAnswer(event: CdkDragDrop<string[]>) {
    const offeredAnswers = this.questionFormGroup.get('question.offeredAnswers') as FormArray;
    moveItemInArray(offeredAnswers.controls, event.previousIndex, event.currentIndex);
    offeredAnswers.updateValueAndValidity({ emitEvent: true });
    this.questionFormGroup.updateValueAndValidity({ emitEvent: true });
  }

  removeRadioAnswer(i: number) {
    let singleSelectionAnswers = this.questionFormGroup.get('question.offeredAnswers') as FormArray;
    singleSelectionAnswers.removeAt(i);
  }

  onRadioCheck(answer: FormGroup, i: number) {
    let targetItem = this.questionFormGroup.get('question.selectedAnswers') as FormArray;
    targetItem.setValue([i]);
  }

  onCheckboxCheck(answer: FormGroup, i: number) {
    let targetItem = this.questionFormGroup.get('question.selectedAnswers') as FormArray;
    let selectedItems = targetItem.value;
    if (selectedItems == '') {
      selectedItems = [];
    }
    if (selectedItems.some(e => e == i)) {
      selectedItems = selectedItems.filter(e => e != i);
      targetItem.setValue([selectedItems]);
    } else {
      targetItem.setValue([...targetItem.value, i]);
    }
  }

  addNewRadioOption() {
    let singleSelectionAnswers = this.questionFormGroup.get('question.offeredAnswers') as FormArray;
    let length = singleSelectionAnswers.value.length;
    singleSelectionAnswers.insert(length, this.fb.group({
      id: ['01'],
      orderNo: [length],
      value: [''],
      remarkAnswer: [false],
      remarkAnswerValue: ['']
    }));
  }

  displayConditions(){
    let conditions = this.questionFormGroup.get('question.conditions') as FormArray;
    while (conditions.length !== 0) {
      conditions.removeAt(0);
    }
    this.selectedConditions.forEach(condition => {
      conditions.push(this.fb.group(condition));
    });
  }


  onRemarkModeChange(answerIndex: number, isRemarkRequired: boolean) {
    let singleSelectionAnswers = this.questionFormGroup.get('question.offeredAnswers') as FormArray;
    singleSelectionAnswers.at(answerIndex).patchValue({ remarkAnswer: isRemarkRequired });
  }

  get questionList() {
    return this.mainForm.get('questionList') as FormArray;
  }

  get questionType() {
    return this.questionFormGroup.get('question.type');
  }

  onClickAddLogicCondition() {
    this.isConditionSectionVisible = true;
  }

  get conditionChoices() {
    return this.questionFormGroup.value.question.offeredAnswers;
  }


}
