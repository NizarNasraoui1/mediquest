package com.qonsult.init;

import com.qonsult.entity.CodeLabel;
import com.qonsult.entity.Question;
import com.qonsult.entity.QuestionnaireModel;
import com.qonsult.entity.Topic;
import com.qonsult.enumeration.QuestionTypeEnum;
import com.qonsult.repository.QuestionnaireRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.*;

@Component("initDenstistQuestionnaire")
@RequiredArgsConstructor
public class InitDenstistQuestionnaire implements DBInitializer{

    private final QuestionnaireRepository questionnaireRepository;
    private final String questionnaireName = "Questionnaire Dentiste";
    public void initQuestionnaire(){
            if(!isAlreadyInitialized()){
                QuestionnaireModel questionnaireModel = new QuestionnaireModel();
                questionnaireModel.setName(questionnaireName);
                List<Topic>topics = new ArrayList<>();
                questionnaireModel.setTopics(topics);
                Topic informationGenerales = new Topic();
                informationGenerales.setName("Informations générales");
                Topic antecedent = new Topic();
                antecedent.setName("Antécédent, problèmes de santé");
                Topic hygiene = new Topic();
                hygiene.setName("Hygiène dentaire");
                topics.addAll(Arrays.asList(informationGenerales,antecedent,hygiene));
                informationGenerales.getQuestions().add(Question.builder()
                        .rank(1)
                        .label("Quel est le motif de votre consultation ?")
                        .type(QuestionTypeEnum.CHECKBOX)
                        .content(new ArrayList<>(Arrays.asList(
                                new CodeLabel("Urgence"),new CodeLabel("Douleur"),new CodeLabel("1ère consultation"),new CodeLabel("Bilan bucco-dentaire (BBD)"),new CodeLabel("Rendez-vous de contrôle"),new CodeLabel("Détartrage"),new CodeLabel("Devis pour prothèse"),new CodeLabel("Autres")
                                ))).build());

                informationGenerales.getQuestions().add(Question.builder()
                        .rank(2)
                        .label("Autre")
                        .type(QuestionTypeEnum.TEXT)
                        .build());

                informationGenerales.getQuestions().add(Question.builder()
                        .rank(3)
                        .label("Avez-vous été adressé par un autre professionnel de santé ?")
                        .type(QuestionTypeEnum.RADIO)
                        .content(new ArrayList<>(Arrays.asList(
                                new CodeLabel("Oui"),new CodeLabel("Non")
                                ))).build());

                informationGenerales.getQuestions().add(Question.builder()
                        .rank(4)
                        .label("Pour quel motif vus-a-t-on adressé ?")
                        .type(QuestionTypeEnum.RADIO)
                        .content(new ArrayList<>(Arrays.asList(
                                new CodeLabel("En prévision d’une opération chirurgicale"),new CodeLabel("En prévision d’un traitement orthodontique"),new CodeLabel("Dans le cadre d’un suivi médical de routine")
                                ))).build());

                informationGenerales.getQuestions().add(Question.builder()
                        .rank(5)
                        .label("Date de votre dernière consultation")
                        .type(QuestionTypeEnum.RADIO)
                        .content(new ArrayList<>(Arrays.asList(
                                new CodeLabel("moins de 6mois"),new CodeLabel("1 an"),new CodeLabel("Plus d’un an")
                                ))).build());
                antecedent.getQuestions().add(Question.builder()
                        .rank(6)
                        .label("Avez vous des problèmes de santé ou antécédents ?")
                        .type(QuestionTypeEnum.RADIO)
                        .content(new ArrayList<>(Arrays.asList(
                                new CodeLabel("Oui"),new CodeLabel("Non")
                                ))).build());


                antecedent.getQuestions().add(Question.builder()
                        .rank(7)
                        .label("Merci de préciser :")
                        .type(QuestionTypeEnum.CHECKBOX)
                        .content(new ArrayList<>(Arrays.asList(
                                new CodeLabel("Cardiaque"),new CodeLabel("Vasculaire"),new CodeLabel("Pulmonaire"),new CodeLabel("Rénal"),new CodeLabel("Diabète"),new CodeLabel("Hépatique"),new CodeLabel("Digestif"),new CodeLabel("Sanguin"),new CodeLabel("Thyroïde"),new CodeLabel("Epileptique"),new CodeLabel("Ophtalmologique"),new CodeLabel("Herpès, Zona"),new CodeLabel("HIV, Sida")
                        ))).build());


                antecedent.getQuestions().add(Question.builder()
                        .rank(8)
                        .label("Précisez d’avantage :")
                        .type(QuestionTypeEnum.TEXT)
                        .build());

                antecedent.getQuestions().add(Question.builder()
                        .rank(6)
                        .label("Avez-vous un médecin traitant ?")
                        .type(QuestionTypeEnum.RADIO)
                        .content(new ArrayList<>(Arrays.asList(
                                new CodeLabel("Oui"),new CodeLabel("Non")
                                ))).build());

                antecedent.getQuestions().add(Question.builder()
                        .rank(8)
                        .label("Quel est son nom ?")
                        .type(QuestionTypeEnum.TEXT)
                        .build());

                antecedent.getQuestions().add(Question.builder()
                        .rank(9)
                        .label("Quelle est son adresse ?")
                        .type(QuestionTypeEnum.TEXT)
                        .build());

                antecedent.getQuestions().add(Question.builder()
                        .rank(10)
                        .label("Avez-vous déjà subit une opération ?")
                        .type(QuestionTypeEnum.RADIO)
                        .content(new ArrayList<>(Arrays.asList(
                                new CodeLabel("Oui"),new CodeLabel("Non")
                                ))).build());

                antecedent.getQuestions().add(Question.builder()
                        .rank(11)
                        .label("Merci de préciser :")
                        .type(QuestionTypeEnum.TEXT)
                        .build());

                antecedent.getQuestions().add(Question.builder()
                        .rank(12)
                        .label("Merci de préciser :")
                        .type(QuestionTypeEnum.CHECKBOX)
                        .content(new ArrayList<>(Arrays.asList(
                                new CodeLabel("Valve cardiaque"),new CodeLabel("Pacemaker"),new CodeLabel("Stents"),new CodeLabel("Pontage"),new CodeLabel("Autres")
                        ))).build());


                antecedent.getQuestions().add(Question.builder()
                        .rank(13)
                        .label("Merci de préciser :")
                        .type(QuestionTypeEnum.TEXT)
                        .build());

                antecedent.getQuestions().add(Question.builder()
                        .rank(14)
                        .label("Êtes-vous actuellement traité à l'hopital ?")
                        .type(QuestionTypeEnum.RADIO)
                        .content(new ArrayList<>(Arrays.asList(
                                new CodeLabel("Oui"),new CodeLabel("Non")
                                ))).build());

                antecedent.getQuestions().add(Question.builder()
                        .rank(15)
                        .label("Merci de préciser :")
                        .type(QuestionTypeEnum.CHECKBOX)
                        .content(new ArrayList<>(Arrays.asList(
                                new CodeLabel("Chimiothérapie"),new CodeLabel("Radiothérapie"),new CodeLabel("Dialyse"),new CodeLabel("Autres")
                        ))).build());


                antecedent.getQuestions().add(Question.builder()
                        .rank(16)
                        .label("Merci de préciser :")
                        .type(QuestionTypeEnum.TEXT)
                        .build());

                antecedent.getQuestions().add(Question.builder()
                        .rank(17)
                        .label("Avez-vous des allergies ?")
                        .type(QuestionTypeEnum.RADIO)
                        .content(new ArrayList<>(Arrays.asList(
                                new CodeLabel("Oui"),new CodeLabel("Non")
                                ))).build());

                antecedent.getQuestions().add(Question.builder()
                        .rank(18)
                        .label("Merci de préciser :")
                        .type(QuestionTypeEnum.CHECKBOX)
                        .content(new ArrayList<>(Arrays.asList(
                                new CodeLabel("Pénicillines"),new CodeLabel("Céphalosporines"),new CodeLabel("Sulfamides"),new CodeLabel("Macrolides"),new CodeLabel("Tétracyclines"),new CodeLabel("Quinolones"),new CodeLabel("Adrenaline"),new CodeLabel("Opioïdes"),new CodeLabel("Anti inflammatoires non steroidiens (AINS)"),new CodeLabel("Iode"),new CodeLabel("Latex"),new CodeLabel("Autres")
                        ))).build());

                antecedent.getQuestions().add(Question.builder()
                        .rank(19)
                        .label("Merci de préciser :")
                        .type(QuestionTypeEnum.TEXT)
                        .build());

                antecedent.getQuestions().add(Question.builder()
                        .rank(20)
                        .label("Prenez vous des médicaments ?")
                        .type(QuestionTypeEnum.RADIO)
                        .content(new ArrayList<>(Arrays.asList(
                                new CodeLabel("Oui"),new CodeLabel("Non")
                                ))).build());

                antecedent.getQuestions().add(Question.builder()
                        .rank(21)
                        .label("Merci de préciser :")
                        .type(QuestionTypeEnum.TEXT)
                        .build());

                antecedent.getQuestions().add(Question.builder()
                        .rank(22)
                        .label("Fumez-vous ?")
                        .type(QuestionTypeEnum.RADIO)
                        .content(new ArrayList<>(Arrays.asList(
                                new CodeLabel("Oui"),new CodeLabel("Non")
                                ))).build());

                antecedent.getQuestions().add(Question.builder()
                        .rank(23)
                        .label("Nombre de cigarette(s) par jour :")
                        .type(QuestionTypeEnum.NUMBER)
                        .build());
                antecedent.getQuestions().add(Question.builder()
                        .rank(24)
                        .label("Depuis combien d’années ?")
                        .type(QuestionTypeEnum.NUMBER)
                        .build());

                antecedent.getQuestions().add(Question.builder()
                        .rank(25)
                        .label("Buvez vous de l'alcool ?")
                        .type(QuestionTypeEnum.RADIO)
                        .content(new ArrayList<>(Arrays.asList(
                                new CodeLabel("Oui"),new CodeLabel("Non")
                                ))).build());

                antecedent.getQuestions().add(Question.builder()
                        .rank(26)
                        .label("Nombre de verre(s) par jour ?")
                        .type(QuestionTypeEnum.NUMBER)
                        .build());

                hygiene.getQuestions().add(Question.builder()
                        .rank(27)
                        .label("Quand vous brossez-vous les dents ?")
                        .type(QuestionTypeEnum.CHECKBOX)
                        .content(new ArrayList<>(Arrays.asList(
                                new CodeLabel("Matin"),new CodeLabel("Midi"),new CodeLabel("Soir")
                        ))).build());

                hygiene.getQuestions().add(Question.builder()
                        .rank(28)
                        .label("Quel matériel utilisez-vous pour vous brosser les dents ?")
                        .type(QuestionTypeEnum.CHECKBOX)
                        .content(new ArrayList<>(Arrays.asList(
                                new CodeLabel("Brosse à dents manuelle"),new CodeLabel("Brosse à dents électrique")
                        ))).build());

                questionnaireRepository.save(questionnaireModel);
            }
    }

    public boolean isAlreadyInitialized(){
        return questionnaireRepository.findByName(questionnaireName).isPresent();
    }

    @Override
    public void init() {
        initQuestionnaire();
    }
}
