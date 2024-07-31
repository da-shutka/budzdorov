package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    @Step("Дождаться загрузки всех попапов и закрыть их")
    public void waitAndRemovePopups(){
        sleep(10000);
        actions().moveByOffset(0, 0).click().build().perform();
    }

    @Step("Открыть главную страницу")
    public MainPage openPage() {
        open("");
        $("h1").shouldHave(text("Заказать лекарства в аптеке через интернет онлайн"));
        waitAndRemovePopups();
        return this;
    }

    @Step("Залогиниться с номером телефона и паролем")
    public MainPage loginWithPhoneAndPassword(String phone, String password) {
        actions().moveToElement($(".header__button_account")).release().perform();
        $("a[href='/customer/account/']").click();
        $("div._text input").sendKeys(phone);
        $("div._password input").sendKeys(password);
        //$("button[type='submit']").click();
        return this;
    }

    @Step("Проверить успешный логин")
    public void checkUserIsLoggedIn(String name) {
        $(".header__button_account").shouldBe(Condition.visible, Duration.ofSeconds(5));
        actions().moveToElement($(".header__button_account")).release().perform();
        $("a[href='/customer/account/']").shouldHave(text(name));
    }
}
