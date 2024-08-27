package web.pages.components;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class PopupComponent {

    private static final SelenideElement
            advPopup = $("div.popup-metadata-popup__paranja"),
            cityPopup = $("div.city-confirm"),
            cookiePopup = $("div.cookie-confirmation-notice");

    public void waitAndRemovePopups() {
        advPopup.should(exist, Duration.ofSeconds(60));
        executeJavaScript("arguments[0].remove();", advPopup);
        executeJavaScript("arguments[0].remove();", cookiePopup);
        executeJavaScript("arguments[0].remove();", cityPopup);
    }
}
