package tests.web;

import io.qameta.allure.Issue;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.MainPage;
import properties.SystemProperties;

public class LoginTests extends TestBase {

    MainPage mainPage = new MainPage();

    @Test
    //@Disabled
    @Issue("JIRA-1234")
    @DisplayName("Успешный логин (но можно заддосить сайт - начинает выдавать 'Неверный логин')")
    public void successfulLoginFromMainPage(){
        String name = "Дарья Петрова";
        mainPage
                .openPage()
                //.loginWithPhoneAndPassword(SystemProperties.login, SystemProperties.password)
                .loginWithEmailAndPassword(SystemProperties.email, SystemProperties.password);
                //.checkUserIsLoggedIn(name);
    }

    @Test
    @DisplayName("Неуспешный логин без телефона")
    public void unsuccessfulLoginWithoutPhoneFromLoginPage(){
        /*loginPage
                .openPage()
                .loginWithPhoneAndPassword("", SystemProperties.password)
                .checkUserIsNotLoggedIn();*/
    }

    @Test
    @DisplayName("Неуспешный логин без пароля")
    public void unsuccessfulLoginWithoutPasswordFromLoginPage(){
        /*loginPage
                .openPage()
                .loginWithPhoneAndPassword(SystemProperties.login, "")
                .checkUserIsNotLoggedIn();*/
    }

    @Test
    @DisplayName("Неуспешный логин с неверными кредами")
    public void unsuccessfulLoginWithIncorrectCredentialsFromLoginPage(){
        /*loginPage
                .openPage()
                .loginWithPhoneAndPassword("test","test")
                .checkUserIsNotLoggedIn();*/
    }
}
