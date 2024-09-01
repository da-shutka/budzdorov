package common.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:properties/auth.properties"
})
public interface AuthConfig extends Config {

    @Key("selenoidLogin")
    String selenoidLogin();

    @Key("selenoidPassword")
    String selenoidPassword();
}