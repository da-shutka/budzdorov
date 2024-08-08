package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    @Step("Открыть главную страницу")
    public MainPage openPage() {
        open("");
        //$("h1").shouldHave(text("Заказать лекарства в аптеке через интернет онлайн"));
        $("h1").shouldHave(text("Онлайн-аптека «Ригла»"));
        waitAndRemovePopups();
        return this;
    }

    @Step("Дождаться загрузки всех попапов и закрыть их")
    public void waitAndRemovePopups(){
        sleep(2000);
        executeJavaScript("$('.popup-metadata-popup__paranja').remove();");
        //actions().moveByOffset(0, 0).click().build().perform();
    }

    /*@Step("Залогиниться с номером телефона и паролем")
    public MainPage loginWithPhoneAndPassword(String phone, String password) {
        actions().moveToElement($(".header__button_account")).release().perform();
        $("a[href='/customer/account/']").click();
        $("div._text input").sendKeys(phone);
        sleep(1000);
        $("div._password input").sendKeys(password);
        sleep(1000);
        $("button[type='submit']").click();
        return this;
    }*/

    @Step("Залогиниться с почтой и паролем")
    public MainPage loginWithEmailAndPassword(String email, String password) {
        sleep(1000);
        $("input[name='userLogin']").sendKeys(email);
        sleep(2000);
        $("input[name='userPassword']").sendKeys(password);
        sleep(2000);
        $("button[type='submit'].btn.send__btn.enter__btn").click();
        sleep(2000);
        return this;
    }

    /*@Step("Проверить успешный логин")
    public void checkUserIsLoggedIn(String name) {
        $(".header__button_account").shouldBe(Condition.visible, Duration.ofSeconds(5));
        actions().moveToElement($(".header__button_account")).release().perform();
        $("a[href='/customer/account/']").shouldHave(text(name));
    }*/
}
