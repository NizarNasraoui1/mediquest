import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuestionnaireLinkListComponent } from './questionnaire-link-list.component';

describe('QuestionnaireLinkListComponent', () => {
  let component: QuestionnaireLinkListComponent;
  let fixture: ComponentFixture<QuestionnaireLinkListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ QuestionnaireLinkListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(QuestionnaireLinkListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
