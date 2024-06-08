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
                label: '',
                items: [
                    {
                        label: 'Dashboard',
                        icon: 'pi pi-fw pi-chart-bar',
                        routerLink: ['./'],
                    },
                ],
            },
            {
                label: '',
                items: [
                    {
                        label: 'Créer un questionnaire',
                        icon: 'pi pi-fw pi-pencil',
                        routerLink: ['./build-questionnaire'],
                    },
                ],
            },
            {
                label: '',
                items: [
                    {
                        label: 'Qr codes',
                        icon: 'pi pi-fw pi-qrcode',
                        routerLink: ['./my-questionnaires'],
                    }
                ],
            },
            {
                label: '',
                items: [
                    {
                        label: 'Boite de réception',
                        icon: 'pi pi-fw pi-envelope',
                        routerLink: ['./mailbox'],
                    },
                ],
            },
            {
                label: "",
                items: [
                    {
                        label: 'Envoyer par SMS/Mail',
                        icon: 'pi pi-fw pi-send',
                        routerLink: ['./sending-box'],
                    },
                ],
            },
            {
                label: '',
                items: [
                    {
                        label: 'Recherche',
                        icon: 'pi pi-fw pi-search',
                        routerLink: ['./history'],
                    },
                ],
            },
            {
                label: '',
                items: [
                    {
                        label: 'Mon Compte',
                        icon: 'pi pi-fw pi-user',
                        routerLink: ['./my-account'],
                    },
                ],
            },
            {
                label: '',
                items: [
                    {
                        label: 'Gestion des utilisateurs',
                        icon: 'pi pi-fw pi-cog',
                        routerLink: ['./user-management'],
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
