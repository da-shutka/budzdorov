package tests.api;

import api.auth.AuthorizationWeb;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import common.config.ProjectConfiguration;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static common.config.ConfigReader.webConfig;

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
