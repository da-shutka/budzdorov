package api.auth;

import io.qameta.allure.Step;
import web.pages.MainPage;

import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class AuthorizationWeb {

    public static final String RIGLA_WEB_SESSION_GUID = "RIGLA_WEB_SESSION_GUID";
    public static String sessionGuidForApi = "";

    @Step("Вход пользователя на сайт")
    public static void enterToWebSite() {
        MainPage mainPage = new MainPage();
        mainPage.openPage();
        sessionGuidForApi = getCookieByName(RIGLA_WEB_SESSION_GUID);
    }

    @Step("Получение {cookie} из установленных cookies")
    public static String getCookieByName(String cookie) {
        String cookieValue = "";
        int attempt = 0;
        
        while(cookieValue == "" && attempt <= 10) {
            try {
                sleep(5000);
                cookieValue = getWebDriver().manage().getCookieNamed(cookie).getValue();
            } catch (Exception e) {
                System.out.println("Не удалось получить куки " + cookie);
            }
            attempt++;
        }
        return cookieValue;
    }
}