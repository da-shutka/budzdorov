package tests.api;

import auth.AuthorizationWeb;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.ProjectConfiguration;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import properties.SystemProperties;

import static config.ConfigReader.webConfig;

public class TestBaseApi {

    @BeforeAll
    static void settingsBeforeAll() {
        ProjectConfiguration projectConfiguration = new ProjectConfiguration(webConfig);
        projectConfiguration.webConfig();

        RestAssured.baseURI = webConfig.baseURI();

        AuthorizationWeb.enterToWebSite();
        Selenide.closeWebDriver();
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
}
