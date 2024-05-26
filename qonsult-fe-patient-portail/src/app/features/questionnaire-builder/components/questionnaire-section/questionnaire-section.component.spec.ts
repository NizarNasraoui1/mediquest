import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuestionnaireSectionComponent } from './questionnaire-section.component';

describe('QuestionnaireSectionComponent', () => {
  let component: QuestionnaireSectionComponent;
  let fixture: ComponentFixture<QuestionnaireSectionComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [QuestionnaireSectionComponent]
    });
    fixture = TestBed.createComponent(QuestionnaireSectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
