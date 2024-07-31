package auth;

import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;
import pages.LoginPage;
import pages.MainPage;
import properties.SystemProperties;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;

public class AuthorizationWeb {

    private static final String login = SystemProperties.login,
            password = SystemProperties.password;

    public static final String CUSTOMER_TOKEN = "customerToken";

    @Step("Авторизация пользователя через UI")
    public static String loginViaUI() {
        LoginPage loginPage = new LoginPage();
        loginPage
                .openPage()
                .loginWithPhoneAndPassword(login, password);
        return getCookieByName(CUSTOMER_TOKEN);
    }

    @Step("Установка cookies")
    public static void addCookies() {
        open("/img/icon-location-dropdown.svg");
        String token = loginViaUI();
        getWebDriver().manage().addCookie(new Cookie(CUSTOMER_TOKEN, token));
    }

    @Step("Получение {cookie} из установленных cookies")
    public static String getCookieByName(String cookie) {
        return getWebDriver().manage().getCookieNamed(cookie).getValue();
    }
}
