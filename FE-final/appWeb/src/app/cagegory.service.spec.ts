import { TestBed } from '@angular/core/testing';

import { CagegoryService } from './cagegory.service';

describe('CagegoryService', () => {
  let service: CagegoryService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CagegoryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
