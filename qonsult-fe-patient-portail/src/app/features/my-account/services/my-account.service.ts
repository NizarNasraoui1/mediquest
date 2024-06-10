import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from 'src/app/shared/models/user';
import { HttpUtilService } from 'src/app/util/service/http-util.service';
import { ChangePassword } from '../models/change-password.model';

const ACCOUNT_MANAGEMENT_BASE_URL = '/api/mediquest/account-management';
const ADMIN_INFORMATIONS_URL = `${ACCOUNT_MANAGEMENT_BASE_URL}/admin-info`;
const CHANGE_PASSWORD_URL = `${ACCOUNT_MANAGEMENT_BASE_URL}/admin-password`;
const CHANGE_ADMIN_INFORMATIONS_URL = `${ACCOUNT_MANAGEMENT_BASE_URL}/admin-informations`;

@Injectable({
  providedIn: 'root'
})

export class MyAccountService {

  constructor(private httpUtil:HttpUtilService) { }

  getAdminInformations():Observable<User>{
    return this.httpUtil.get(ADMIN_INFORMATIONS_URL);
  }

  upateAdminInformations(user:User):Observable<User>{
    return this.httpUtil.put(CHANGE_ADMIN_INFORMATIONS_URL,user);
  }

  changePassword(changePassword:ChangePassword){
    return this.httpUtil.put(CHANGE_PASSWORD_URL,changePassword);
  }
}
