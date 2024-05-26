import { TestBed } from '@angular/core/testing';

import { QuestionnaireBuilderService } from './questionnaire-builder.service';

describe('QuestionnaireBuilderService', () => {
  let service: QuestionnaireBuilderService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(QuestionnaireBuilderService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
