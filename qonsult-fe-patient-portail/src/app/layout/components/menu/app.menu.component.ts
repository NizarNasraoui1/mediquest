import { OnInit } from '@angular/core';
import { Component } from '@angular/core';
import { LayoutService } from '../../service/app.layout.service';

@Component({
    selector: 'app-menu',
    templateUrl: './app.menu.component.html',
})
export class AppMenuComponent implements OnInit {
    model: any[] = [];

    constructor(public layoutService: LayoutService) {}

    ngOnInit() {
        this.model = [
            {
                label: 'Reporting',
                items: [
                    {
                        label: 'Dashboard',
                        icon: 'pi pi-fw pi-chart-bar',
                        routerLink: ['./'],
                    },
                ],
            },
            {
                label: 'Créer un questionnaire',
                items: [
                    {
                        label: 'Dashboard',
                        icon: 'pi pi-fw pi-pencil',
                        routerLink: ['./build-questionnaire'],
                    },
                ],
            },
            {
                label: 'Mes QR codes',
                items: [
                    {
                        label: 'Qr codes',
                        icon: 'pi pi-fw pi-qrcode',
                        routerLink: ['./my-questionnaires'],
                    },
                    {
                        label: 'Gestion des favoris',
                        icon: 'pi pi-fw pi-star',
                        routerLink: ['./my-questionnaires/favourites'],
                    }
                ],
            },
            {
                label: 'Boite de réception',
                items: [
                    {
                        label: 'Boite de réception',
                        icon: 'pi pi-fw pi-envelope',
                        routerLink: ['./mailbox'],
                    },
                ],
            },
            {
                label: "Boite d'envoie",
                items: [
                    {
                        label: 'Envoyer par SMS/Mail',
                        icon: 'pi pi-fw pi-envelope',
                        routerLink: ['./sending-box'],
                    },
                ],
            },
            {
                label: 'Historique des questionnaires',
                items: [
                    {
                        label: 'Recherche',
                        icon: 'pi pi-fw pi-search',
                        routerLink: ['./history'],
                    },
                ],
            }
            // {
            //     label: 'Hierarchy',
            //     items: [
            //         {
            //             label: 'Submenu 1', icon: 'pi pi-fw pi-bookmark',
            //             items: [
            //                 {
            //                     label: 'Submenu 1.1', icon: 'pi pi-fw pi-bookmark',
            //                     items: [
            //                         { label: 'Submenu 1.1.1', icon: 'pi pi-fw pi-bookmark' },
            //                         { label: 'Submenu 1.1.2', icon: 'pi pi-fw pi-bookmark' },
            //                         { label: 'Submenu 1.1.3', icon: 'pi pi-fw pi-bookmark' },
            //                     ]
            //                 },
            //                 {
            //                     label: 'Submenu 1.2', icon: 'pi pi-fw pi-bookmark',
            //                     items: [
            //                         { label: 'Submenu 1.2.1', icon: 'pi pi-fw pi-bookmark' }
            //                     ]
            //                 },
            //             ]
            //         },
            //         {
            //             label: 'Submenu 2', icon: 'pi pi-fw pi-bookmark',
            //             items: [
            //                 {
            //                     label: 'Submenu 2.1', icon: 'pi pi-fw pi-bookmark',
            //                     items: [
            //                         { label: 'Submenu 2.1.1', icon: 'pi pi-fw pi-bookmark' },
            //                         { label: 'Submenu 2.1.2', icon: 'pi pi-fw pi-bookmark' },
            //                     ]
            //                 },
            //                 {
            //                     label: 'Submenu 2.2', icon: 'pi pi-fw pi-bookmark',
            //                     items: [
            //                         { label: 'Submenu 2.2.1', icon: 'pi pi-fw pi-bookmark' },
            //                     ]
            //                 },
            //             ]
            //         }
            //     ]
            // },
        ];
    }
}
