import { TestBed } from '@angular/core/testing';

import { SendingBoxService } from './sending-box.service';

describe('SendingBoxService', () => {
  let service: SendingBoxService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SendingBoxService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
