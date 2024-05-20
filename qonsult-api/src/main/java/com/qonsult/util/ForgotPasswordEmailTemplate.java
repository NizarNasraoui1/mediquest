package com.qonsult.util;

public class ForgotPasswordEmailTemplate {
    public static String generateForgotMailTemplate(String resetPasswordUrl,String emailToken){
        return "<!DOCTYPE html>\n" +
                "<html lang=\"fr\">\n" +
                "<head>\n" +
                "<meta charset=\"UTF-8\">\n" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "<style>\n" +
                "  /* Ajoutez des styles ici qui sont supportés par la plupart des clients de messagerie */\n" +
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
                "    <h1>Réinitialisation de votre mot de passe</h1>\n" +
                "    <div class=\"email-content\">\n" +
                "      <p>Bonjour,</p>\n" +
                "      <p>Vous avez demandé à réinitialiser votre mot de passe. Veuillez cliquer sur le bouton ci-dessous pour choisir un nouveau mot de passe.</p>\n" +
                "      <!-- Remplacez l'URL href par le lien de réinitialisation de mot de passe -->\n" +
                "      <a href='" +resetPasswordUrl+"/"+emailToken+"'"+
                " class=\"button\"><span class=\"button-text\">Réinitialiser le mot de passe</span></a>\n" +
                "      <p>Si vous n'avez pas demandé à réinitialiser votre mot de passe, veuillez ignorer cet email ou nous prévenir.</p>\n" +
                "    </div>\n" +
                "    <div class=\"footer\">\n" +
                "      <p>Si vous ignorez cette demande, aucun changement ne sera effectué.</p>\n" +
                "      <p>&copy; 2023 Mediquest</p>\n" +
                "    </div>\n" +
                "  </div>\n" +
                "</body>\n" +
                "</html>\n";
    }
}
