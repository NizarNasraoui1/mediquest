import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Group } from 'src/app/shared/models/group';
import { User } from 'src/app/shared/models/user';
import { HttpUtilService } from 'src/app/util/service/http-util.service';

const USER_MANAGEMENT_BASE_URL = "/api/mediquest/account-management";
const GET_USERS_URL = `${USER_MANAGEMENT_BASE_URL}/users`;
const GET_GROUPS_URL = `${USER_MANAGEMENT_BASE_URL}/groups`;
const GET_ROLES_URL = `${USER_MANAGEMENT_BASE_URL}/users`;


@Injectable({
  providedIn: 'root'
})
export class UserManagementService {

  constructor(private httpUtil:HttpUtilService) { }

  getUsers():Observable<User[]>{
    return this.httpUtil.get(GET_USERS_URL);
  }

  getGroups():Observable<Group[]>{
    return this.httpUtil.get(GET_GROUPS_URL);
  }
}
