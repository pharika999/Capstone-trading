import { TestBed } from '@angular/core/testing';

import { AuthenticationguardService } from './authenticationguard.service';

describe('AuthenticationguardService', () => {
  let service: AuthenticationguardService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AuthenticationguardService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
