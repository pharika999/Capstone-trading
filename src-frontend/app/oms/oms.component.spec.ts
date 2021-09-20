import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OmsComponent } from './oms.component';

describe('OmsComponent', () => {
  let component: OmsComponent;
  let fixture: ComponentFixture<OmsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OmsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OmsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
