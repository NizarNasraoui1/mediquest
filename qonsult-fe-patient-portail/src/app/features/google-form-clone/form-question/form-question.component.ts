import { CdkDragDrop, moveItemInArray } from '@angular/cdk/drag-drop';
import { ChangeDetectorRef, Component, Input, OnInit, Output, ViewEncapsulation } from '@angular/core';
import {
    FormArray,
    FormBuilder,
    FormControl,
    FormGroup,
    FormGroupDirective,
} from '@angular/forms';
import { _ } from 'lodash';

@Component({
    selector: 'form-question',
    templateUrl: './form-question.component.html',
    styleUrls: ['./form-question.component.css'],
    // encapsulation: ViewEncapsulation.None
})
export class FormQuestionComponent implements OnInit {
    @Input() questionFormGroup!: FormGroup;
    @Input() questionIndex: number;
    selectedQuestionType: string;
    isConditionSectionVisible = false;

    questionsTypes = [
        {
            name: 'Choix unique',
            value: 'singleSelection',
        },
        {
            name: 'Choix multiple',
            value: 'multipleSelection',
        },
        {
            name: 'Texte',
            value: 'text'
        }
    ];

    mainForm: FormGroup;
    constructor(
        public rootFormGroup: FormGroupDirective,
        public fb: FormBuilder,
        private cdRef: ChangeDetectorRef
    ) {}

    newTextFormGroup() {
        const typedForm: FormGroup<any> = new FormGroup<any>({
            id: new FormControl('q1'),
            orderNo: new FormControl(0),
            type: new FormControl('question'),
            required: new FormControl(false),
            question: new FormGroup<any>({
                text: new FormControl(''),
                placeholder: new FormControl(''),
                type: new FormControl('freeText'),
                selectedAnswers: new FormArray([]),
                offeredAnswers: new FormArray([]),
            }),
        });

        return typedForm;
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
                type: ['freeText'],
                selectedAnswers: this.fb.array([
                    this.fb.group({
                        id: ['01'],
                        orderNo: [1],
                        value: [''],
                        remarkAnswer: [true],
                        remarkAnswerValue: ['idk'],
                    }),
                ]),
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

    get newOfferedAnswers() {
        return this.fb.array([
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
                value: [''],
                remarkAnswer: [false],
                remarkAnswerValue: ['idk'],
            }),
        ]);
    }



    ngOnInit() {
        this.mainForm = this.rootFormGroup.control;
    }

    onChangeQuestionType(i: number) {
        let currentQuestion = this.questionList.at(i) as FormGroup;
        const selectedType = this.questionFormGroup.get('question.type').value;
        const orderNo = this.questionFormGroup.get('orderNo').value;

        // this.questionList.removeAt(1);
        if (selectedType === 'freeText') {
            currentQuestion.setValue(this.newTextFormGroup);
            currentQuestion.patchValue({
                orderNo: orderNo,
            });
        } else if (selectedType === 'singleSelection' || selectedType === 'multipleSelection') {
            currentQuestion.patchValue({
                orderNo: orderNo,
                question: {
                    type: selectedType,
                },
            });

            let selectedAnswers = currentQuestion.get(
                'question.selectedAnswers',
            ) as FormArray;
            selectedAnswers.patchValue([]);

            let offeredAnswers = currentQuestion.get(
                'question.offeredAnswers',
            ) as FormArray;

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
        offeredAnswers.updateValueAndValidity({ emitEvent: true }); // Make sure to emit the event
        this.questionFormGroup.updateValueAndValidity({ emitEvent: true });
    }

    removeRadioAnswer(i: number) {
        // remove the target in form array
        let singleSelectionAnswers = this.questionFormGroup.get(
            'question.offeredAnswers',
        ) as FormArray;
        let targetItem = singleSelectionAnswers.at(i);
        let targetItemOrder = targetItem.get('orderNo').value;
        singleSelectionAnswers.removeAt(i);

        if (i !== 0) {
            // update order No
            let prevOrderItem = singleSelectionAnswers.at(i - 1) as FormGroup;
            let nextOrderItem = singleSelectionAnswers.at(i) as FormGroup;
            prevOrderItem.patchValue({
                orderNo: targetItemOrder - 1,
            });
            nextOrderItem.patchValue({
                orderNo: targetItemOrder,
            });
        }
    }

    onRadioCheck(answer: FormGroup, i: number) {
        let targetItem = this.questionFormGroup.get(
            'question.selectedAnswers',
        ) as FormArray;
        targetItem.setValue([i]);
    }

    onCheckboxCheck(answer: FormGroup, i: number) {
        let targetItem = this.questionFormGroup.get(
            'question.selectedAnswers',
        ) as FormArray;
        let selectedItems = targetItem.value;
        let answerValue = i;
        if (selectedItems == '') {
            selectedItems = [];
        }
        if (selectedItems.some((e) => e == answerValue)) {
            selectedItems = selectedItems.filter((e) => e != answerValue);
            targetItem.setValue([selectedItems]);
        } else {
            targetItem.setValue([...targetItem.value, i]);
        }
    }

    addNewRadioOption() {
        let singleSelectionAnswers = this.questionFormGroup.get(
            'question.offeredAnswers',
        ) as FormArray;
        let length = singleSelectionAnswers.value.length;
        let newFormGroup: FormGroup = this.fb.group({
            id: ['01'],
            orderNo: [length],
            value: [''],
            remarkAnswer: [false],
            remarkAnswerValue: [''],
        });
        singleSelectionAnswers.insert(length, newFormGroup);

        // Update the order No
        const nextNumberInOrder = singleSelectionAnswers.at(length) as FormGroup;
        if (nextNumberInOrder) {
            nextNumberInOrder.patchValue({
                orderNo: length + 1,
            });
        }
    }

    onRemarkModeChange(answerIndex: number, isRemarkRequired: boolean) {
        let singleSelectionAnswers = this.questionFormGroup.get(
            'question.offeredAnswers',
        ) as FormArray;

        let selectedAns = singleSelectionAnswers.at(answerIndex);
        selectedAns.patchValue({
            remarkAnswer: isRemarkRequired,
        });
    }

    get questionList() {
        return this.mainForm.get('questionList') as FormArray;
    }

    get questionDetails() {
        return this.questionFormGroup.controls;
    }

    get questionType() {
        return this.questionFormGroup.get('question.type');
    }

    get singleSelectionAnswers() {
        return this.questionFormGroup.get('question.offeredAnswers')[
            'controls'
        ];
    }

    get conditionChoices(){
        console.log(this.questionFormGroup.value.question.offeredAnswers)
        return this.questionFormGroup.value.question.offeredAnswers;
    }
    onClickAddLogicCondition(){
        this.isConditionSectionVisible = true;
    }
}
