import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpUtilService } from 'src/app/util/service/http-util.service';

@Injectable()
export class DashboardService {
    constructor(private httpUtil: HttpUtilService) {}
}
