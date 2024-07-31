package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage {

    @Step("Открыть страницу логина")
    public LoginPage openPage() {
        open("/customer/account/login");
        $(".login__section-description-title").shouldHave(text("Войти с учетной записью Будь Здоров"));
        return this;
    }

    @Step("Залогиниться с номером телефона и паролем")
    public LoginPage loginWithPhoneAndPassword(String phone, String password) {
        $(".login-form__phone input").sendKeys(phone);
        $(".login-form__password input").sendKeys(password);
        $("button[type='submit']").click();
        sleep(5000);
        return this;
    }

    @Step("Проверить успешный логин")
    public void checkUserIsLoggedIn(String name) {
        $(".header__button_account").shouldBe(Condition.visible, Duration.ofSeconds(5));
        actions().moveToElement($(".header__button_account")).release().perform();
        $("a[href='/customer/account/']").shouldHave(text(name));
    }
}
