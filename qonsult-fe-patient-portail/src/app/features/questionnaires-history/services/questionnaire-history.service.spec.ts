import { TestBed } from '@angular/core/testing';

import { QuestionnaireHistoryService } from './questionnaire-history.service';

describe('QuestionnaireHistoryService', () => {
  let service: QuestionnaireHistoryService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(QuestionnaireHistoryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
