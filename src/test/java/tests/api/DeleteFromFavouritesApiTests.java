package tests.api;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import tests.TestData;

@DisplayName("API тесты на удаление товара из избранного")
@Tags({
        @Tag("API"),
        @Tag("FAV"),
        @Tag("FAV_DEL")
})
public class DeleteFromFavouritesApiTests extends TestBaseApi {

    TestSteps steps = new TestSteps();

    @Test
    @Owner("Дарья Петрова")
    @Feature("Реализация списка избранных товаров пользователя")
    @Story("Удаление товара из списка избранного")
    @DisplayName("Удаление товара из избранного")
    public void deleteProductFromFavouritesApi() {
        steps.addProductToFavourites(TestData.product);
        steps.removeFromFavourites(TestData.product);
        steps.checkFavouritesListIsEmpty();
    }

    @Test
    @Owner("Дарья Петрова")
    @Feature("Реализация списка избранных товаров пользователя")
    @Story("Удаление товара из списка избранного")
    @DisplayName("Удаление товара с несуществующим id из избранного")
    public void deleteNonexistingProductFromFavouritesApi() {
        steps.unsuccessfullyRemoveFromFavourites(TestData.unknownProduct);
    }

    @Test
    @Owner("Дарья Петрова")
    @Feature("Реализация списка избранных товаров пользователя")
    @Story("Удаление товара из списка избранного")
    @DisplayName("Удаление товара из избранного без авторизации")
    public void deleteProductFromFavouritesWithoutCookieApi() {
        steps.deleteProductFromFavouritesNoSessionGuid(TestData.product);
    }
}
