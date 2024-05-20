import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuestionnairesHistoryComponent } from './questionnaires-history.component';

describe('QuestionnairesHistoryComponent', () => {
  let component: QuestionnairesHistoryComponent;
  let fixture: ComponentFixture<QuestionnairesHistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ QuestionnairesHistoryComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(QuestionnairesHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
