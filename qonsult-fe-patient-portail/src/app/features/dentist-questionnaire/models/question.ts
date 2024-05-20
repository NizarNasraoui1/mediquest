import { CodeLabel } from "src/app/shared/models/code-label";
import { Condition } from "./condition";
import { QuestionTypeEnum } from "./question-type.enum";

export interface Question {
    id: string;
    label: string;
    type: QuestionTypeEnum;
    rank:number;
    content?: CodeLabel[];
    conditions?: Condition[];
    topic: string;
}
