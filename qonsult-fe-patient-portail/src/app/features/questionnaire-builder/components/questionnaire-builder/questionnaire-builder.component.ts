import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { QuestionnaireBuilderService } from '../../services/questionnaire-builder.service';
import { ToasterService } from 'src/app/shared/services/toast.service';

@Component({
  selector: 'app-questionnaire-builder',
  templateUrl: './questionnaire-builder.component.html',
  styleUrls: ['./questionnaire-builder.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class QuestionnaireBuilderComponent implements OnInit {
    form: FormGroup;

  constructor(private fb: FormBuilder,private questionnaireBuilderService:QuestionnaireBuilderService,private toasterService:ToasterService) {
    this.form = this.fb.group({
      sections: this.fb.array([]),
      questionnaireTitle:['']
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
    this.questionnaireBuilderService.saveQuestionnaire(this.form.value).subscribe((res)=>{
        this.toasterService.addSuccessMessage("Le Questionnaire a été enregistré avec succèes");
        setTimeout(() => {
            window.location.reload();
        }, 2000);
    })
  }
}
