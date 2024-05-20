package com.qonsult.util;

public class QuestionnaireInvitationMailTemplate {
    public static String generateQuestionnaireInvitationMailTemplate(String url) {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"fr\">\n" +
                "<head>\n" +
                "<meta charset=\"UTF-8\">\n" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "<style>\n" +
                "  body {\n" +
                "    margin: 0;\n" +
                "    padding: 0;\n" +
                "    font-family: Arial, sans-serif;\n" +
                "    font-size: 16px;\n" +
                "    line-height: 1.6;\n" +
                "    color: #333333;\n" +
                "  }\n" +
                "  .email-container {\n" +
                "    max-width: 600px;\n" +
                "    margin: 0 auto;\n" +
                "    background: #ffffff;\n" +
                "    padding: 20px;\n" +
                "    text-align: center;\n" +
                "    background-color: #F7F9F9;\n" +
                "  }\n" +
                "  .email-content {\n" +
                "    margin: 20px 0;\n" +
                "  }\n" +
                "  .button {\n" +
                "    display: inline-block;\n" +
                "    padding: 10px 20px;\n" +
                "    margin: 20px 0;\n" +
                "    background-color: #0066cc;\n" +
                "    color: #ffffff;\n" +
                "    text-decoration: none;\n" +
                "    border-radius: 5px;\n" +
                "  }\n" +
                "  .footer {\n" +
                "    font-size: 12px;\n" +
                "    color: #777777;\n" +
                "    margin-top: 20px;\n" +
                "  }\n" +
                "  .button-text{\n" +
                "\tcolor:white;\n" +
                "  }\n" +
                "  p{\n" +
                "\tcolor:black;\n" +
                "  }\n" +
                "  h1{\n" +
                "\tcolor:black;\n" +
                "  }\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <div class=\"email-container\">\n" +
                "    <h1>Questionnaire Médical</h1>\n" +
                "    <div class=\"email-content\">\n" +
                "      <p>Bonjour,</p>\n" +
                "      <p>Vous ête invité à remplir le questionnaire en cliquant sur le lien ci dessous.</p>\n" +
                "      <a href='" + url + "'" +
                " class=\"button\"><span class=\"button-text\">Lien</span></a>\n" +
                "    </div>\n" +
                "    <div class=\"footer\">\n" +
                "      <p>&copy; 2023 Mediquest</p>\n" +
                "    </div>\n" +
                "  </div>\n" +
                "</body>\n" +
                "</html>\n";
    }
}
