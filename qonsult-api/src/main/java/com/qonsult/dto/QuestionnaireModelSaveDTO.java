package com.qonsult.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestionnaireModelSaveDTO {
    private String questionnaireTitle;
    private List<Section> sections;

    @Getter
    @Setter
    public static class Section {
        private Questions questions;

        @Getter
        @Setter
        public static class Questions {
            private String sectionTitle;
            private List<SectionQuestion> sectionQuestions;

            @Getter
            @Setter
            public static class SectionQuestion {
                private String text;
                private String type;
                private List<Integer> selectedAnswers;
                private List<String> offeredAnswers;
                private List<Integer> conditions;
            }
        }
    }
}