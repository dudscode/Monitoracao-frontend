import { TestBed } from '@angular/core/testing';

import { ErrorMonitoringService } from './error-monitoring.service';

describe('ErrorMonitoringService', () => {
  let service: ErrorMonitoringService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ErrorMonitoringService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
