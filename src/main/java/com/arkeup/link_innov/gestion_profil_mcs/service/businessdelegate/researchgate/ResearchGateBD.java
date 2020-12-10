package com.arkeup.link_innov.gestion_profil_mcs.service.businessdelegate.researchgate;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ResearchGateLoginException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ResearchGateParseException;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.reasearchgate.ProfilResearchGate;
import com.jayway.jsonpath.JsonPath;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ResearchGateBD {

    String loginUrl = "https://www.researchgate.net/login";
    String profileUrl =  "https://www.researchgate.net/account.ProfileSettings.html";
    String settingsUrl = "https://www.researchgate.net/account.AccountSettings.html";



    public ProfilResearchGate extractProfileInfo(String user, String password) throws IOException, ResearchGateParseException, ResearchGateLoginException {
        ProfilResearchGate profilResearchGate = new ProfilResearchGate();

        Map cookies = researchGateLogin(user, password);

        profilResearchGate = populateWithSettingInfo(cookies, profilResearchGate);

        profilResearchGate = populateWithProfileInfo(cookies, profilResearchGate);

        return profilResearchGate;

    }

    private Map researchGateLogin(String user, String password) throws IOException, ResearchGateParseException, ResearchGateLoginException {
        // récupération de la page de login avec les cookies et le request token
        Connection.Response responseLoginPage = Jsoup.connect(loginUrl)
                .method(Connection.Method.GET)
                .execute();


        Elements elements = responseLoginPage.parse().select("form[name=\"loginForm\"]");

        if(elements.isEmpty()) {
            throw new ResearchGateParseException("LoginForm not found");
        }

        Element loginForm = elements.first();

        elements = loginForm.select("input[name=\"request_token\"]");

        if(elements.isEmpty()) {
            throw new ResearchGateParseException("request_token not found");
        }

        Element requestTokenInput = elements.first();

        if(requestTokenInput.hasAttr("value") == false){
            throw new ResearchGateParseException("Input with name equal request_token does not have attribute value");
        }

        String requesToken = requestTokenInput.attr("value");


        // s'authentifier en utilisant les cookies et les informations du formulaire de login
        Connection.Response responseAuth =  Jsoup.connect(loginUrl).
                data("request_token",requesToken,
                        "login", user,
                        "password", password).
                method(Connection.Method.POST)
                .cookies(responseLoginPage.cookies())
                .execute();



        Document authResultPage = responseAuth.parse();
        Element loginField = authResultPage.getElementById("input-login");
        Element recaptchaField = authResultPage.getElementById("recaptcha-accessible-status");

        if(loginField != null && loginField.hasClass("is-invalid")){
            throw new ResearchGateLoginException("username or password incorrect");
        }
        else if (recaptchaField != null){
            throw new ResearchGateLoginException("recaptcha activated :/");
        }




        return responseAuth.cookies();
    }

    private ProfilResearchGate populateWithSettingInfo(Map<String, String> cookies, ProfilResearchGate profilResearchGate) throws ResearchGateParseException, IOException {

        if(profilResearchGate == null){
            throw new ResearchGateParseException("ObjectProfile must not be null");
        }

        //get Settings info
        Connection.Response responseSettingsPage = Jsoup.connect(settingsUrl).
                method(Connection.Method.GET)
                .cookies(cookies)
                .execute();


        Document settingsPageDoc = responseSettingsPage.parse();

        Elements mailListElements = settingsPageDoc.select("*.account-email-list-item");

        if(mailListElements.isEmpty()){
            throw new ResearchGateParseException("No mail found in settings page");
        }

        for (Element mailListElement: mailListElements) {

            if(mailListElement.toString().contains("Primary email")){
                Elements mailItemElements = mailListElement.select("*.nova-l-flex__item");

                if(mailItemElements.isEmpty()){
                    throw new ResearchGateParseException("No mail found in primary element");
                }

                for (Element mailItemElement : mailItemElements) {
                    //si ce n'est pas le label c'est forcément l'adresse mail
                    if(mailItemElement.text().contains("Primary email") == false){
                        profilResearchGate.setEmail(mailItemElement.text());
                        break;
                    }
                }
            }
        }

        return profilResearchGate;
    }

    private ProfilResearchGate populateWithProfileInfo(Map<String, String> cookies, ProfilResearchGate profilResearchGate) throws ResearchGateParseException, IOException {
        //get ProfilResearchGate info
        Connection.Response responseProfilePage = Jsoup.connect(profileUrl).
                method(Connection.Method.GET)
                .cookies(cookies)
                .execute();


        Document profilePageDoc = responseProfilePage.parse();

        Element genderElement = profilePageDoc.getElementById("gender");

        Elements genderSelectedElements = genderElement.select("*[selected]");

        if(genderSelectedElements.isEmpty()){
            throw new ResearchGateParseException("No gender element found in profilResearchGate page");
        }

        if(genderSelectedElements.first().hasAttr("value") == false){
            throw new ResearchGateParseException("No value attribute found in selected element");
        }

        profilResearchGate.setGender(genderSelectedElements.first().attr("value"));


        /**
         * dans le javascript de la page récupéré, il y a un long JSON contenant toutes les information nécéssaire.
         * le pattern suivant fait en sorte de récupérer la partie JSON qui nous intéresse.
         * Ce JSON sera parsé pour récupérer les informations nécéssaire.
          */
        Pattern p = Pattern.compile("\"profileAccountNamesWidget\":\\{\"data\":(.*}),");
        Matcher m = p.matcher(profilePageDoc.toString());
        while(m.find())
        {
            String json = m.group(1);

            profilResearchGate.setFirstname(JsonPath.read(json, "$.firstname").toString());
            profilResearchGate.setLastname(JsonPath.read(json, "$.lastname").toString());
        }

        return profilResearchGate;
    }
}
