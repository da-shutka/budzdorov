package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage {

    private static final SelenideElement
            advPopup = $("div.popup-metadata-popup__paranja"),
            cityPopup = $("div.city-confirm"),
            cookiePopup = $("div.cookie-confirmation-notice"),
            headerContainer = $("div.header-wrapper__i"),
            loginInput = $("input[name='userLogin']"),
            passwordInput = $("input[name='userPassword']"),
            submitButton = $("button.send__btn"),
            notification = $("p.notificator-container__notification-text");

    @Step("Открыть страницу логина")
    public LoginPage openPage() {
        open("/customer/account/login");
        $("h2").shouldHave(text("Вход на сайт"));
        waitAndRemovePopups();
        return this;
    }

    @Step("Дождаться загрузки всех попапов и удалить их")
    public void waitAndRemovePopups() {
        sleep(2000);
        executeJavaScript("arguments[0].remove();", advPopup);
        executeJavaScript("arguments[0].remove();", cityPopup);
        executeJavaScript("arguments[0].remove();", cookiePopup);
    }

    @Step("Залогиниться с почтой и паролем")
    public LoginPage loginWithEmailAndPassword(String email, String password) {
        executeJavaScript("arguments[0].remove();", headerContainer);
        loginInput.scrollTo().setValue(email);
        passwordInput.scrollTo().setValue(password);
        submitButton.scrollTo().click();
        return this;
    }

    @Step("Проверить успешный логин")
    public void checkUserIsLoggedIn() {
        notification.shouldHave(text("Вы успешно вошли на сайт!"));
    }

    @Step("Проверить неуспешный логин из-за капчи")
    public void checkCaptchaValidateError() {
        notification.shouldHave(text("Captcha validate error"));
    }

    @Step("Проверить, что прользователь не залогинен из-за незаданного логина")
    public void checkUserIsNotLoggedInEmptyLogin() {
        $("h2").shouldHave(text("Вход на сайт"));
        loginInput.sibling(0).shouldHave(text("Поле обязательно для заполнения"));
    }

    @Step("Проверить, что прользователь не залогинен из-за незаданного пароля")
    public void checkUserIsNotLoggedInEmptyPassword() {
        $("h2").shouldHave(text("Вход на сайт"));
        passwordInput.sibling(1).shouldHave(text("Поле обязательно для заполнения"));
    }

    @Step("Проверить неуспешный логин из-за неверных данных")
    public void checkUserIsNotLoggedInIncorrectCredentials() {
        notification.shouldHave(
                text("Вы ввели некорректные данные или ваша учётная запись временно заблокирована.")
        );
    }
}
