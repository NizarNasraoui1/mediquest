import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SendingBoxComponent } from './sending-box.component';

describe('SendingBoxComponent', () => {
  let component: SendingBoxComponent;
  let fixture: ComponentFixture<SendingBoxComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [SendingBoxComponent]
    });
    fixture = TestBed.createComponent(SendingBoxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
