import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MailConfirmedComponent } from './mail-confirmed.component';

describe('MailConfirmedComponent', () => {
  let component: MailConfirmedComponent;
  let fixture: ComponentFixture<MailConfirmedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MailConfirmedComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MailConfirmedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
