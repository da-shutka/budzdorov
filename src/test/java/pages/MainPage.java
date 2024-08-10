package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    private final SelenideElement
            advPopup = $(".popup-metadata-popup__paranja"),
            cityPopup = $("div.city-confirm"),
            accountDropdown = $("div.account-dropdown"),
            loginButton = $("a._login");
            //modalTabEmail = $(".tabs__tab").shouldHave(text("Email"));

    @Step("Открыть главную страницу")
    public MainPage openPage() {
        open("/");
        $("h1").shouldHave(text("Онлайн-аптека «Ригла»"));
        waitAndRemovePopups();
        return this;
    }

    @Step("Дождаться загрузки всех попапов и закрыть их")
    public void waitAndRemovePopups(){
        sleep(2000);
        executeJavaScript("arguments[0].remove();", advPopup);
        executeJavaScript("arguments[0].remove();", cityPopup);
    }
}
