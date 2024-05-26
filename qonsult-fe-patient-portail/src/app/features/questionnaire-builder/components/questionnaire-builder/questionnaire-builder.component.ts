import { Component, ViewEncapsulation } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-questionnaire-builder',
  templateUrl: './questionnaire-builder.component.html',
  styleUrls: ['./questionnaire-builder.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class QuestionnaireBuilderComponent {
    form: FormGroup;

    constructor(private fb: FormBuilder) {
      this.form = this.fb.group({
        items: this.fb.array([])
      });
    }

    get items() {
      return this.form.get('items') as FormArray;
    }

    addItem() {
      const itemForm = this.fb.group({
        section: ['', Validators.required]
      });
      this.items.push(itemForm);
    }

    removeItem(index: number) {
      this.items.removeAt(index);
    }

    onSubmit() {
      console.log(this.form.value);
    }
}
