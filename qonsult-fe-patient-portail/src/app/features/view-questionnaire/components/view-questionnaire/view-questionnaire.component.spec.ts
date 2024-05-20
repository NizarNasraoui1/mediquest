import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewQuestionnaireComponent } from './view-questionnaire.component';

describe('ViewQuestionnaireComponent', () => {
  let component: ViewQuestionnaireComponent;
  let fixture: ComponentFixture<ViewQuestionnaireComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ViewQuestionnaireComponent]
    });
    fixture = TestBed.createComponent(ViewQuestionnaireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
