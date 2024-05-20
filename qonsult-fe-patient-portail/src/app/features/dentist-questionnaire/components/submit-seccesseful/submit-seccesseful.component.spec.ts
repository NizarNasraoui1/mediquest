import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubmitSeccessefulComponent } from './submit-seccesseful.component';

describe('SubmitSeccessefulComponent', () => {
  let component: SubmitSeccessefulComponent;
  let fixture: ComponentFixture<SubmitSeccessefulComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SubmitSeccessefulComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SubmitSeccessefulComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
