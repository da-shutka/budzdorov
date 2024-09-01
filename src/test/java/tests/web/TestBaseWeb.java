package tests.web;

import api.auth.AuthorizationApi;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import common.config.AuthConfig;
import common.config.WebConfig;
import common.helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class TestBaseWeb {

    @BeforeAll
    static void settingsBeforeAll() {
        WebConfig webConfig = ConfigFactory.create(WebConfig.class, System.getProperties());
        AuthConfig authConfig = ConfigFactory.create(AuthConfig.class, System.getProperties());

        Configuration.baseUrl = webConfig.baseUrl();
        Configuration.browser = webConfig.browser();
        Configuration.browserVersion = webConfig.browserVersion();
        Configuration.browserSize = webConfig.browserSize();
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 10000;

        if (webConfig.isRemote()) {
            Configuration.remote = "https://"
                    + authConfig.selenoidLogin()
                    + ":"
                    + authConfig.selenoidPassword()
                    + "@"
                    + webConfig.wdHost()
                    + "/wd/hub";

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true
            ));
            Configuration.browserCapabilities = capabilities;
        }
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        AuthorizationApi.addCookies();
    }

    @AfterEach
    void afterEach() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        if (System.getProperty("isRemote") != null && System.getProperty("isRemote").equals("true")) {
            Attach.addVideo(System.getProperty("wdHost"));
        }
        Selenide.closeWebDriver();
    }
}