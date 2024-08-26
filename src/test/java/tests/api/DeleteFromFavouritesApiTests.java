package tests.api;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import common.TestData;

@DisplayName("API тесты на удаление товара из избранного")
@Tag("API")
public class DeleteFromFavouritesApiTests extends TestBaseApi {

    TestSteps steps = new TestSteps();

    @Test
    @Owner("Дарья Петрова")
    @Feature("Реализация списка избранных товаров пользователя")
    @Story("API: Удаление товара из списка избранного")
    @DisplayName("Проверка удаления товара из избранного")
    public void deleteProductFromFavouritesApi() {
        steps.addProductToFavourites(TestData.product);
        steps.removeFromFavourites(TestData.product);
        steps.checkFavouritesListIsEmpty();
    }

    @Test
    @Owner("Дарья Петрова")
    @Feature("Реализация списка избранных товаров пользователя")
    @Story("API: Удаление товара из списка избранного")
    @DisplayName("Проверка удаления товара с несуществующим id из избранного")
    public void deleteNonexistingProductFromFavouritesApi() {
        steps.checkDeleteNonexistingProductFromFavourites(TestData.unknownProduct);
    }

    @Test
    @Owner("Дарья Петрова")
    @Feature("Реализация списка избранных товаров пользователя")
    @Story("API: Удаление товара из списка избранного")
    @DisplayName("Проверка удаления товара из избранного без авторизации")
    public void deleteProductFromFavouritesWithoutCookieApi() {
        steps.checkDeleteProductFromFavouritesNoSessionGuid(TestData.product);
    }
}
