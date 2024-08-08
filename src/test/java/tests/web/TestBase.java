package tests.web;

import auth.AuthorizationWeb;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import properties.SystemProperties;

import java.util.List;
import java.util.Map;

public class TestBase {

    @BeforeAll
    static void settingsBeforeAll() {
        //RestAssured.baseURI = "https://www.budzdorov.ru/rest";
        RestAssured.baseURI = "https://www.rigla.ru/rest";
        Configuration.pageLoadStrategy = "eager";
        Configuration.browser = SystemProperties.browser;
        Configuration.browserSize = SystemProperties.browserSize;
        Configuration.browserVersion = SystemProperties.browserVersion;

        Configuration.baseUrl = "https://www.rigla.ru/";
        //Configuration.remote = "https://user1:1234@" + SystemProperties.wdHost + "/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        //AuthorizationWeb.setCookiesForApi();
    }

    @AfterEach
    void afterEach() {
//        Attach.screenshotAs("Last screenshot");
//        Attach.pageSource();
//        Attach.browserConsoleLogs();
//        Attach.addVideo();
        Selenide.closeWebDriver();
    }
}
