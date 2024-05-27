import { Component, forwardRef } from '@angular/core';
import { ControlValueAccessor, NG_VALUE_ACCESSOR } from '@angular/forms';

@Component({
  selector: 'app-questionnaire-section',
  templateUrl: './questionnaire-section.component.html',
  styleUrls: ['./questionnaire-section.component.scss'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => QuestionnaireSectionComponent),
      multi: true
    }
  ]
})
export class QuestionnaireSectionComponent implements ControlValueAccessor {
    sectionQuestions: string = '';
    sectionTitle: string = '';
    value: { sectionTitle: string; sectionQuestions: string } = { sectionTitle: '', sectionQuestions: '' };
    onChange: (value: any) => void = () => {};
    onTouched: () => void = () => {};

    writeValue(value: any): void {
        if (value) {
            this.sectionTitle = value.sectionTitle || '';
            this.sectionQuestions = value.sectionQuestions || '';
            this.value = { sectionTitle: this.sectionTitle, sectionQuestions: this.sectionQuestions };
        }
    }

    registerOnChange(fn: (value: any) => void): void {
      this.onChange = fn;
    }

    registerOnTouched(fn: () => void): void {
      this.onTouched = fn;
    }

    setDisabledState?(isDisabled: boolean): void {
      // Handle the disabled state here
    }

    handleBlur(): void {
      this.onTouched();
    }

    setSectionQuestions(sectionQuestions: string): void {
      this.sectionQuestions = sectionQuestions;
      this.updateValue();
    }

    onSectionTitleChange(newTitle: string): void {
      this.sectionTitle = newTitle;
      this.updateValue();
    }

    updateValue(): void {
        this.value = {
            sectionTitle: this.sectionTitle,
            sectionQuestions: this.sectionQuestions
        };
        this.onChange(this.value);
    }
}
