package tests.web;

import org.junit.jupiter.api.*;
import pages.LoginPage;
import properties.SystemProperties;

@DisplayName("Тесты на авторизацию")
@Tags({@Tag("WEB"), @Tag("LOGIN")})
public class LoginWebTests extends TestBaseWeb {

    LoginPage loginPage = new LoginPage();

    @Test
    @Disabled
    @DisplayName("Успешный логин")
    public void successfulLoginFromLoginPage(){
        loginPage
                .openPage()
                .loginWithEmailAndPassword(SystemProperties.email, SystemProperties.password)
                .checkUserIsLoggedIn();
    }

    @Test
    @DisplayName("Неуспешный логин из-за капчи")
    public void unsuccessfulLoginDueToCaptchaFromLoginPage(){
        loginPage
                .openPage()
                .loginWithEmailAndPassword(SystemProperties.email, SystemProperties.password)
                .checkCaptchaValidateError();
    }

    @Test
    @DisplayName("Неуспешный логин без почты")
    public void unsuccessfulLoginWithoutEmailFromLoginPage(){
        loginPage
                .openPage()
                .loginWithEmailAndPassword("", SystemProperties.password)
                .checkUserIsNotLoggedInEmptyLogin();
    }

    @Test
    @DisplayName("Неуспешный логин без пароля")
    public void unsuccessfulLoginWithoutPasswordFromLoginPage(){
        loginPage
                .openPage()
                .loginWithEmailAndPassword(SystemProperties.email, "")
                .checkUserIsNotLoggedInEmptyPassword();
    }

    @Test
    @Disabled
    @DisplayName("Неуспешный логин с неверными кредами")
    public void unsuccessfulLoginWithIncorrectCredentialsFromLoginPage(){
        loginPage
                .openPage()
                .loginWithEmailAndPassword("test","test")
                .checkUserIsNotLoggedInIncorrectCredentials();
    }
}
