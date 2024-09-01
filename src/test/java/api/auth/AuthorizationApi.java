package api.auth;

import org.openqa.selenium.Cookie;

import static api.specs.RiglaSpec.requestSpec;
import static api.specs.RiglaSpec.responseSpecWithStatusCode200;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;

public class AuthorizationApi {

    public static final String RIGLA_WEB_SESSION_GUID = "RIGLA_WEB_SESSION_GUID";
    static final String COOKIE_CONFIRMATION = "cookieConfirmation";
    static final String REGION_SUGGESTED = "regionSuggested";
    public static String sessionGuidForApi = "";

    public static void addCookies() {
        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie(COOKIE_CONFIRMATION, "true"));
        getWebDriver().manage().addCookie(new Cookie(REGION_SUGGESTED, "1"));
    }

    public static void addToken() {
        sessionGuidForApi = given(requestSpec)
                .when()
                .post("/V1/web-session/create")
                .then()
                .spec(responseSpecWithStatusCode200)
                .extract().as(String.class);
    }

}