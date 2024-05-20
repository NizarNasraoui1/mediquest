import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuestionnaireLinkComponent } from './questionnaire-link.component';

describe('QuestionnaireLinkComponent', () => {
  let component: QuestionnaireLinkComponent;
  let fixture: ComponentFixture<QuestionnaireLinkComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ QuestionnaireLinkComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(QuestionnaireLinkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
