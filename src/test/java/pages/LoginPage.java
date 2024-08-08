package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage {

    private static final WebElement
            advPopup = $(".popup-metadata-popup__paranja"),
            cityPopup = $("div.city-confirm");

    @Step("Открыть страницу логина")
    public LoginPage openPage() {
        open("/customer/account/login");
        //$("h2").shouldHave(text("Вход на сайт"));
        waitAndRemovePopups();
        return this;
    }

    @Step("Дождаться загрузки всех попапов и удалить их")
    public void waitAndRemovePopups(){
        sleep(2000);
        executeJavaScript("arguments[0].remove();", advPopup);
        executeJavaScript("arguments[0].remove();", cityPopup);
    }

    /*@Step("Залогиниться с номером телефона и паролем")
    public void loginWithPhoneAndPassword(String phone, String password) {
        sleep(1000);
        $("input[name='userPhone']").sendKeys(phone);
        sleep(2000);
        $("input[name='userPassword']").sendKeys(password);
        sleep(2000);
        $("button[type='submit']").click();
        sleep(2000);
    }*/

    @Step("Залогиниться с почтой и паролем")
    public void loginWithEmailAndPassword(String email, String password) {
        sleep(1000);
        $("input[name='userLogin']").click();
        $("input[name='userLogin']").sendKeys(email);
        sleep(2000);
        $("input[name='userPassword']").click();
        $("input[name='userPassword']").sendKeys(password);
        sleep(2000);

        //actions().moveToElement($("button[type='submit'].btn.send__btn.enter__btn")).moveByOffset(200, 10).click().perform();
        //$("button[type='submit'].btn.send__btn.enter__btn").click();
        $("button[type='submit'].btn.send__btn.enter__btn").sendKeys(Keys.ENTER);
        sleep(2000);
    }

    /*@Step("Проверить успешный логин")
    public void checkUserIsLoggedIn(String name) {
        $(".header__button_account").shouldBe(Condition.visible, Duration.ofSeconds(5));
        actions().moveToElement($(".header__button_account")).release().perform();
        $("a[href='/customer/account/']").shouldHave(text(name));
    }*/
}
