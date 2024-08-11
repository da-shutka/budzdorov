package tests.api;

import auth.AuthorizationWeb;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import properties.SystemProperties;

import java.util.Map;

public class TestBaseApi {

    @BeforeAll
    static void settingsBeforeAll() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.browser = SystemProperties.browser;
        Configuration.browserSize = SystemProperties.browserSize;
        Configuration.browserVersion = SystemProperties.browserVersion;
        Configuration.baseUrl = "https://www.rigla.ru/";
        //Configuration.remote = "https://user1:1234@" + SystemProperties.wdHost + "/wd/hub";

        RestAssured.baseURI = "https://www.rigla.ru/rest";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;

        AuthorizationWeb.enterToWebSite();
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void afterAll() {
        Selenide.closeWebDriver();
    }
}
