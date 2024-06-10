import { Group } from "./group"

export interface User{
    id: number,
    firstName: string,
    lastName: string,
    username: string,
    password: string,
    email: string,
    address:string
    group: Group
}
