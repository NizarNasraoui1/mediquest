import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Group } from 'src/app/shared/models/group';
import { User } from 'src/app/shared/models/user';
import { HttpUtilService } from 'src/app/util/service/http-util.service';

const USER_MANAGEMENT_BASE_URL = "/api/mediquest/account-management";
const GET_USERS_URL = `${USER_MANAGEMENT_BASE_URL}/users`;
const GET_GROUPS_URL = `${USER_MANAGEMENT_BASE_URL}/groups`;
const GET_ROLES_URL = `${USER_MANAGEMENT_BASE_URL}/role-management`;
const PUT_ROLES_URL = `${USER_MANAGEMENT_BASE_URL}/group-roles`;
const ADD_USER_TO_GROUP_URL = `${USER_MANAGEMENT_BASE_URL}/groups`;


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

  getRoleManagement(){
    return this.httpUtil.get(GET_ROLES_URL);
  }

  updateRoles(groupRoles){
    return this.httpUtil.put(PUT_ROLES_URL,groupRoles);
  }

  addUser(user,groupId){
    return this.httpUtil.post(`${ADD_USER_TO_GROUP_URL}/${groupId}/users`,user);
  }

  addGroup(group){
    return this.httpUtil.post(`${ADD_USER_TO_GROUP_URL}`,group);
  }

}
