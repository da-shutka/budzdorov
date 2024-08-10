package auth;

import io.qameta.allure.Step;
import properties.SystemProperties;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;

public class AuthorizationWeb {

    private static final String
            //login = SystemProperties.login,
            email = SystemProperties.email,
            password = SystemProperties.password;
    public static final String
            RIGLA_WEB_SESSION_GUID = "RIGLA_WEB_SESSION_GUID",
            CUSTOMER_TOKEN = "customerToken";
    public static String
            sessionGuidForApi = "",
            webToken = "";

    /*@Step("Авторизация пользователя через UI")
    public static void loginViaUI() {
        LoginPage loginPage = new LoginPage();
        loginPage
                .openPage()
                //.loginWithPhoneAndPassword(login, password);
                .loginWithEmailAndPassword(email, password);
        sessionGuidForApi = getCookieByName(RIGLA_WEB_SESSION_GUID);
        webToken = getCookieByName(CUSTOMER_TOKEN);
    }*/

    @Step("Получение {cookie} из установленных cookies")
    public static String getCookieByName(String cookie) {
        String cookieValue = "";
        try {
            cookieValue = getWebDriver().manage().getCookieNamed(cookie).getValue();
        } catch (Exception e) {
            System.out.println("Не удалось получить куки " + cookie);
        }
        return cookieValue;
    }

    /*@Step("Установка cookies")
    public static void addCookies() {
        open("/img/icon-location-dropdown.svg");
        getWebDriver().manage().addCookie(new Cookie(CUSTOMER_TOKEN, webToken));
    }*/

    @Step("Сохранение cookies для API")
    public static void setCookiesForApi() {
        sessionGuidForApi = getCookieByName(RIGLA_WEB_SESSION_GUID);
    }
}
