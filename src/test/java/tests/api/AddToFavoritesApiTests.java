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

@DisplayName("API тесты на добавление товара в избранное")
@Tag("API")
public class AddToFavoritesApiTests extends TestBaseApi {

    final TestStepsApi apiSteps = new TestStepsApi();
    final String product = getTestData("product");
    final String unknownProduct = getTestData("unknownProduct");


    @Test
    @WithToken
    @Owner("Дарья Петрова")
    @Feature("Реализация списка избранных товаров пользователя")
    @Story("API: Добавление товара в список избранного")
    @DisplayName("Проверка добавления товара в избранное")
    public void checkProductAddingToFavoritesApiTest() {
        apiSteps.addProductToFavorites(product);
        apiSteps.checkProductIsInFavorites(product);
        apiSteps.removeFromFavorites(product);
    }

    @Test
    @WithToken
    @Owner("Дарья Петрова")
    @Feature("Реализация списка избранных товаров пользователя")
    @Story("API: Добавление товара в список избранного")
    @DisplayName("Проверка добавления несуществующего товара в избранное")
    public void checkNonexistentProductAddingToFavoritesApiTest() {
        apiSteps.addProductToFavorites(unknownProduct);
        apiSteps.checkFavoritesListIsEmpty();
    }

    @Test
    @WithToken
    @Owner("Дарья Петрова")
    @Feature("Реализация списка избранных товаров пользователя")
    @Story("API: Добавление товара в список избранного")
    @DisplayName("Проверка добавления товара в избранное дважды")
    public void checkProductAddingToFavoritesTwiceApiTest() {
        apiSteps.addProductToFavorites(product);
        apiSteps.addProductToFavorites(product);
        apiSteps.checkProductIsInFavorites(product);

        apiSteps.removeFromFavorites(product);
    }

    @Test
    @WithToken
    @Owner("Дарья Петрова")
    @Feature("Реализация списка избранных товаров пользователя")
    @Story("API: Добавление товара в список избранного")
    @DisplayName("Проверка добавления товара в избранное без авторизации")
    public void checkProductAddingToFavoritesWithoutCookieApiTest() {
        apiSteps.checkAddProductToFavoritesNoSessionGuid(product);
    }
}
