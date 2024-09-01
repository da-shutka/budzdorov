package common.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:properties/${env}.properties"
})
public interface WebConfig extends Config {

    @Key("wdHost")
    @DefaultValue("")
    String wdHost();

    @Key("browser")
    @DefaultValue("chrome")
    String browser();

    @Key("browserSize")
    @DefaultValue("1600x1200")
    String browserSize();

    @Key("browserVersion")
    @DefaultValue("127")
    String browserVersion();

    @Key("baseUrl")
    @DefaultValue("https://www.rigla.ru")
    String baseUrl();

    @Key("isRemote")
    @DefaultValue("false")
    boolean isRemote();
}