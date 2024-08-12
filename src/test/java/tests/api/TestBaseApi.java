package tests.api;

import auth.AuthorizationWeb;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import properties.SystemProperties;

public class TestBaseApi {

    @BeforeAll
    static void settingsBeforeAll() {
        Configuration.baseUrl = "https://www.rigla.ru/";
        Configuration.pageLoadStrategy = "eager";
        Configuration.browser = SystemProperties.browser;
        Configuration.browserSize = SystemProperties.browserSize;
        Configuration.browserVersion = SystemProperties.browserVersion;
        Configuration.remote ="https://"
                + SystemProperties.selenoidLogin
                + ":"
                + SystemProperties.selenoidPassword
                + "@"
                + SystemProperties.wdHost
                + "/wd/hub";

        RestAssured.baseURI = "https://www.rigla.ru/rest";

        AuthorizationWeb.enterToWebSite();
        Selenide.closeWebDriver();
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
}
