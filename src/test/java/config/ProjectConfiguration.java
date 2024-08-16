package config;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.remote.DesiredCapabilities;
import tests.TestData;

import java.util.Map;

public class ProjectConfiguration {

    private final WebConfig webConfig;

    public ProjectConfiguration(WebConfig webConfig) {
        this.webConfig = webConfig;
    }

    public void webConfig() {
        Configuration.baseUrl = webConfig.baseUrl();
        Configuration.browser = webConfig.browser();
        Configuration.browserVersion = webConfig.browserVersion();
        Configuration.browserSize = webConfig.browserSize();
        Configuration.pageLoadStrategy = "eager";

        TestData.email = webConfig.email();
        TestData.password = webConfig.password();

        if (webConfig.isRemote()) {
            Configuration.remote ="https://"
                    + webConfig.selenoidLogin()
                    + ":"
                    + webConfig.selenoidPassword()
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
}