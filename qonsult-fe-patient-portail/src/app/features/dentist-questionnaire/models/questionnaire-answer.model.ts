import { GeneralInformation } from "../../view-questionnaire/models/general-informations.model";
import { Certification } from "./certification.model";
import { QuestionAnswer } from "./question-answer";
import { Questionnaire } from "./questionnaire";

export interface QuestionnaireAnswer{
    id?:number,
    appointmentDate:Date;
    questionnaire: Questionnaire,
    patientInformation: GeneralInformation,
    questionAnswers: QuestionAnswer[],
    certification: Certification
}
