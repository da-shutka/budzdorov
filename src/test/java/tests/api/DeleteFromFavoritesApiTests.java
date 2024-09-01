package tests.api;

import api.helpers.WithToken;
import api.steps.TestStepsApi;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static common.TestData.getTestData;

@DisplayName("API тесты на удаление товара из избранного")
@Tag("API")
public class DeleteFromFavoritesApiTests extends TestBaseApi {

    final TestStepsApi apiSteps = new TestStepsApi();
    String product = getTestData("product");
    String unknownProduct = getTestData("unknownProduct");

    @Test
    @WithToken
    @Owner("Дарья Петрова")
    @Feature("Реализация списка избранных товаров пользователя")
    @Story("API: Удаление товара из списка избранного")
    @DisplayName("Проверка удаления товара из избранного")
    public void checkProductDeletingFromFavoritesApiTest() {
        apiSteps.addProductToFavorites(product);
        apiSteps.removeFromFavorites(product);
        apiSteps.checkFavoritesListIsEmpty();
    }

    @Test
    @WithToken
    @Owner("Дарья Петрова")
    @Feature("Реализация списка избранных товаров пользователя")
    @Story("API: Удаление товара из списка избранного")
    @DisplayName("Проверка удаления товара с несуществующим id из избранного")
    public void checkNonexistentProductDeletingFromFavoritesApiTest() {
        apiSteps.checkDeleteNonexistentProductFromFavorites(unknownProduct);
    }

    @Test
    @WithToken
    @Owner("Дарья Петрова")
    @Feature("Реализация списка избранных товаров пользователя")
    @Story("API: Удаление товара из списка избранного")
    @DisplayName("Проверка удаления товара из избранного без авторизации")
    public void checkProductDeletingFromFavoritesWithoutCookieApiTest() {
        apiSteps.checkDeleteProductFromFavoritesNoSessionGuid(product);
    }
}
