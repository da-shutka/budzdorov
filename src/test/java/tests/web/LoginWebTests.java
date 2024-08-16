package tests.web;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import web.pages.LoginPage;
import common.TestData;

@DisplayName("UI тесты на авторизацию")
@Tags({
        @Tag("WEB"),
        @Tag("LOGIN")
})
public class LoginWebTests extends TestBaseWeb {

    LoginPage loginPage = new LoginPage();

    @Test
    @Disabled
    @Owner("Дарья Петрова")
    @Feature("Реализация логина на сайте")
    @Story("UI: Успешный логин")
    @DisplayName("Успешный логин")
    public void successfulLoginFromLoginPage(){
        loginPage
                .openPage()
                .loginWithEmailAndPassword(TestData.email, TestData.password)
                .checkUserIsLoggedIn();
    }

    @Test
    @Owner("Дарья Петрова")
    @Feature("Реализация логина на сайте")
    @Story("UI: Неуспешный логин")
    @DisplayName("Неуспешный логин из-за капчи")
    public void unsuccessfulLoginDueToCaptchaFromLoginPage(){
        loginPage
                .openPage()
                .loginWithEmailAndPassword(TestData.email, TestData.password)
                .checkCaptchaValidateError();
    }

    @Test
    @Owner("Дарья Петрова")
    @Feature("Реализация логина на сайте")
    @Story("UI: Неуспешный логин")
    @DisplayName("Неуспешный логин без почты")
    public void unsuccessfulLoginWithoutEmailFromLoginPage(){
        loginPage
                .openPage()
                .loginWithEmailAndPassword("", TestData.password)
                .checkUserIsNotLoggedInEmptyLogin();
    }

    @Test
    @Owner("Дарья Петрова")
    @Feature("Реализация логина на сайте")
    @Story("UI: Неуспешный логин")
    @DisplayName("Неуспешный логин без пароля")
    public void unsuccessfulLoginWithoutPasswordFromLoginPage(){
        loginPage
                .openPage()
                .loginWithEmailAndPassword(TestData.email, "")
                .checkUserIsNotLoggedInEmptyPassword();
    }

    @Test
    @Disabled
    @Owner("Дарья Петрова")
    @Feature("Реализация логина на сайте")
    @Story("UI: Неуспешный логин")
    @DisplayName("Неуспешный логин с неверными кредами")
    public void unsuccessfulLoginWithIncorrectCredentialsFromLoginPage(){
        loginPage
                .openPage()
                .loginWithEmailAndPassword("test","test")
                .checkUserIsNotLoggedInIncorrectCredentials();
    }
}
