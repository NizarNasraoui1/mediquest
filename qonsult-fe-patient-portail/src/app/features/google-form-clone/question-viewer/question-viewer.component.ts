import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-question-viewer',
  templateUrl: './question-viewer.component.html',
  styleUrls: ['./question-viewer.component.css'],
})
export class QuestionViewerComponent implements OnInit {
  @Input() questionFormGroup: any;
  @Input() i: number;
  constructor() {}

  ngOnInit() {}

  get questionType() {
    return this.questionFormGroup.get('question.type');
  }
}
