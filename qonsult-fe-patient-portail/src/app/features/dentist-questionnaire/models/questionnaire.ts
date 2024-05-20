import { Topic } from "./topic.model";

export interface Questionnaire{
    id:number,
    name:string,
    topics: Topic[];
}
