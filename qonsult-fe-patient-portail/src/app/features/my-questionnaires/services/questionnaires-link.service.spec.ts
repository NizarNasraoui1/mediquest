import { TestBed } from '@angular/core/testing';

import { QuestionnairesLinkService } from './questionnaires-link.service';

describe('QuestionnairesLinkService', () => {
  let service: QuestionnairesLinkService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(QuestionnairesLinkService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
