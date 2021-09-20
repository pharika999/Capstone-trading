import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TradinghistoryComponent } from './tradinghistory.component';

describe('TradinghistoryComponent', () => {
  let component: TradinghistoryComponent;
  let fixture: ComponentFixture<TradinghistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TradinghistoryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TradinghistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
