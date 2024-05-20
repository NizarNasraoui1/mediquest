import { TestBed } from '@angular/core/testing';

import { ViewQuestionnaireService } from './view-questionnaire.service';

describe('ViewQuestionnaireService', () => {
  let service: ViewQuestionnaireService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ViewQuestionnaireService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
