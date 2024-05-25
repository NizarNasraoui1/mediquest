import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuestionnaireAlreadyPassedComponent } from './questionnaire-already-passed.component';

describe('QuestionnaireAlreadyPassedComponent', () => {
  let component: QuestionnaireAlreadyPassedComponent;
  let fixture: ComponentFixture<QuestionnaireAlreadyPassedComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [QuestionnaireAlreadyPassedComponent]
    });
    fixture = TestBed.createComponent(QuestionnaireAlreadyPassedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
