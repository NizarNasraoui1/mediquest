import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-questionnaire-builder',
  templateUrl: './questionnaire-builder.component.html',
  styleUrls: ['./questionnaire-builder.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class QuestionnaireBuilderComponent implements OnInit {
    form: FormGroup;

  constructor(private fb: FormBuilder) {
    this.form = this.fb.group({
      sections: this.fb.array([])
    });
  }
    ngOnInit(): void {
        this.addItem();
    }

  get sections() {
    return this.form.get('sections') as FormArray;
  }

  addItem() {
    const itemForm = this.fb.group({
      questions: ['', Validators.required]
    });
    this.sections.push(itemForm);
  }

  removeItem(index: number) {
    this.sections.removeAt(index);
  }

  onSubmit() {
    console.log(this.form.value);
  }
}
