import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuestionnaireBuilderComponent } from './questionnaire-builder.component';

describe('QuestionnaireBuilderComponent', () => {
  let component: QuestionnaireBuilderComponent;
  let fixture: ComponentFixture<QuestionnaireBuilderComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [QuestionnaireBuilderComponent]
    });
    fixture = TestBed.createComponent(QuestionnaireBuilderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
