import { Signature } from "src/app/shared/models/signature"
import { QuestionTypeEnum } from "../../dentist-questionnaire/models/question-type.enum"
import { GeneralInformation } from "./general-informations.model"


export interface QuestionResponse{
    id:number,
    questionType: QuestionTypeEnum,
    question: string,
    response: string[]
}

export interface Topic{
    id:number,
    name:string,
    content: QuestionResponse[]
}

export interface QuestionnaireResponse{
    name: string,
    generalInformations: GeneralInformation,
    topics: Topic[],
    signature?: Signature

}
