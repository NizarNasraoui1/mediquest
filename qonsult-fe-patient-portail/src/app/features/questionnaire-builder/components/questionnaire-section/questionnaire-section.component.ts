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
export class QuestionnaireSectionComponent implements ControlValueAccessor{
    value;
    onChange: (value: string) => void = () => {};
    onTouched: () => void = () => {};

    writeValue(value: string): void {
      this.value = value;
    }

    registerOnChange(fn: (value: string) => void): void {
      this.onChange = fn;
    }

    registerOnTouched(fn: () => void): void {
      this.onTouched = fn;
    }

    setDisabledState?(isDisabled: boolean): void {
      // Handle the disabled state here
    }

    handleInputChange(event: Event): void {
      const input = event.target as HTMLInputElement;
      this.value = input.value;
      this.onChange(this.value);
    }

    handleBlur(): void {
      this.onTouched();
    }
}
