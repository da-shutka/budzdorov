package web.pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage {

    private final SelenideElement
            headerContainer = $("div.header-wrapper__i"),
            loginInput = $("input[name='userLogin']"),
            passwordInput = $("input[name='userPassword']"),
            submitButton = $("button.send__btn"),
            notification = $("p.notificator-container__notification-text"),
            wishListPanel = $(".wish-list.popup-right");

    @Step("Открыть страницу логина по ссылке https://www.rigla.ru/customer/account/login")
    public LoginPage openPage() {
        open("/customer/account/login");
        $("h2").shouldHave(text("Вход на сайт"));
        Configuration.timeout = 30000;
        wishListPanel.should(exist);
        Configuration.timeout = 10000;
        return this;
    }

    @Step("Ввести почту '{email}' и пароль '{password}' и нажать 'Вход'")
    public LoginPage loginWithEmailAndPassword(String email, String password) {
        executeJavaScript("arguments[0].remove();", headerContainer);
        loginInput.setValue(email);
        passwordInput.setValue(password);
        submitButton.click();
        return this;
    }

    @Step("Проверить, что пользователь залогинен" +
            "и отобразилось уведомление 'Вы успешно вошли на сайт!'")
    public void checkUserIsLoggedIn() {
        notification.shouldHave(text("Вы успешно вошли на сайт!"));
    }

    @Step("Проверить, что пользователь залогинен и открылся Личный кабинет")
    public LoginPage checkUserIsInPersonalAccount() {
        $("h2").shouldHave(text("Личный кабинет"));
        return this;
    }

    @Step("Проверить, что пользователь не залогинен, остался на странице логина")
    public LoginPage checkUserStayedAtTheSamePage() {
        $("h2").shouldHave(text("Вход на сайт"));
        return this;
    }

    @Step("Проверить, что отобразилось уведомление 'Captcha validate error'")
    public void checkCaptchaValidateError() {
        notification.shouldHave(text("Captcha validate error"));
    }

    @Step("Проверить, что под полем Логин - валидационное сообщение 'Поле обязательно для заполнения'")
    public void checkUserIsNotLoggedInEmptyLogin() {
        loginInput.sibling(0).shouldHave(text("Поле обязательно для заполнения"));
    }

    @Step("Проверить, что под полем Пароль - валидационное сообщение 'Поле обязательно для заполнения'")
    public void checkUserIsNotLoggedInEmptyPassword() {
        passwordInput.sibling(1).shouldHave(text("Поле обязательно для заполнения"));
    }

    @Step("Проверить, что отобразилось уведомление " +
            "'Вы ввели некорректные данные или ваша учётная запись временно заблокирована.'")
    public void checkUserIsNotLoggedInIncorrectCredentials() {
        notification.shouldHave(
                text("Вы ввели некорректные данные или ваша учётная запись временно заблокирована.")
        );
    }
}
