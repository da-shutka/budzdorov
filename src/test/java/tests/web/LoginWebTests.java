package tests.web;

import common.TestData;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import web.pages.LoginPage;

@DisplayName("UI тесты на авторизацию")
@Tag("WEB")
@Slf4j
public class LoginWebTests extends TestBaseWeb {

    final LoginPage loginPage = new LoginPage();

    @Test
    //@Disabled
    @Owner("Дарья Петрова")
    @Feature("Реализация логина на сайте")
    @Story("UI: Успешный логин")
    @DisplayName("Проверка успешного логина")
    public void checkSuccessfulLoginFromLoginPageTest() {
        log.info("email: {}", TestData.email);
        log.info("password: {}", TestData.password);
        loginPage
                .openPage()
                .loginWithEmailAndPassword(TestData.email, TestData.password)
                .checkUserIsInPersonalAccount()
                .checkUserIsLoggedIn();
    }

    @Test
    @Owner("Дарья Петрова")
    @Feature("Реализация логина на сайте")
    @Story("UI: Неуспешный логин")
    @DisplayName("Проверка неуспешного логина из-за капчи")
    public void checkUnsuccessfulLoginDueToCaptchaFromLoginPageTest() {
        loginPage
                .openPage()
                .loginWithEmailAndPassword(TestData.email, TestData.password)
                .checkUserStayedAtTheSamePage()
                .checkCaptchaValidateError();
    }

    @Test
    @Owner("Дарья Петрова")
    @Feature("Реализация логина на сайте")
    @Story("UI: Неуспешный логин")
    @DisplayName("Проверка неуспешного логина без почты")
    public void checkUnsuccessfulLoginWithoutEmailFromLoginPageTest() {
        loginPage
                .openPage()
                .loginWithEmailAndPassword("", TestData.password)
                .checkUserStayedAtTheSamePage()
                .checkUserIsNotLoggedInEmptyLogin();
    }

    @Test
    @Owner("Дарья Петрова")
    @Feature("Реализация логина на сайте")
    @Story("UI: Неуспешный логин")
    @DisplayName("Проверка неуспешного логина без пароля")
    public void checkUnsuccessfulLoginWithoutPasswordFromLoginPageTest() {
        loginPage
                .openPage()
                .loginWithEmailAndPassword(TestData.email, "")
                .checkUserStayedAtTheSamePage()
                .checkUserIsNotLoggedInEmptyPassword();
    }

    @Test
    @Disabled
    @Owner("Дарья Петрова")
    @Feature("Реализация логина на сайте")
    @Story("UI: Неуспешный логин")
    @DisplayName("Проверка неуспешного логина с неверными кредами")
    public void checkUnsuccessfulLoginWithIncorrectCredentialsFromLoginPageTest() {
        loginPage
                .openPage()
                .loginWithEmailAndPassword("test", "test")
                .checkUserStayedAtTheSamePage()
                .checkUserIsNotLoggedInIncorrectCredentials();
    }
}
