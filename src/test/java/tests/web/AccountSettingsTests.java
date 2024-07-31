package tests.web;

import helpers.WithLogin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AccountSettingsTests {

    @Test
    @WithLogin
    @DisplayName("Изменение города")
    public void removeProductFromFavourites(){
        user.setCity(idMoscow);
        AccountPage
                .openPage()
                .changeAccountCity(Spb);
        user.checkSityIsSet(idSpb);
        user.setCity(idMoscow);
    }
}
