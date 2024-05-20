import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { MenuItem } from 'primeng/api';
import { AuthService } from 'src/app/core/_services/auth.service';
import { LayoutService } from '../../service/app.layout.service';

@Component({
    selector: 'app-topbar',
    templateUrl: './app.topbar.component.html',
})
export class AppTopBarComponent {
    items!: MenuItem[];

    constructor(
        public layoutService: LayoutService,
        private authService: AuthService,
        private router: Router,
    ) {}

    logOut() {
        this.authService.logOut();
        this.router.navigate(['/']);
    }
}
