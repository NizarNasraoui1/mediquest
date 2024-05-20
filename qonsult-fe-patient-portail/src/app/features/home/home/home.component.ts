import { HttpClient } from '@angular/common/http';
import { Component, ElementRef, OnInit, Renderer2, ViewEncapsulation } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  encapsulation: ViewEncapsulation.ShadowDom
})
export class HomeComponent implements OnInit {
    htmlContent: string = '';

    constructor(private router:Router) { }

    ngOnInit(): void {

    }

    navigateToRegister(){
        this.router.navigate(['auth/register']);
    }

}
