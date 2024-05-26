import { Component, Input, OnInit } from '@angular/core';
import { FormGroup, FormGroupDirective } from '@angular/forms';

@Component({
  selector: 'google-form-clone-model',
  templateUrl: './google-form-clone-model.component.html',
  styleUrls: ['./google-form-clone-model.component.css'],
})
export class GoogleFormCloneModelComponent implements OnInit {
  @Input() mainForm!: FormGroup;

  strValue: string;

  constructor(public rootFormGroup: FormGroupDirective) {}

  ngOnInit() {
  }
}
