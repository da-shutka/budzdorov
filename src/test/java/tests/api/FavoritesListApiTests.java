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

@DisplayName("API тесты на список товаров в избранном")
@Tag("API")
public class FavoritesListApiTests extends TestBaseApi {

    final TestStepsApi steps = new TestStepsApi();
    final String product = getTestData("product");
    final String product2 = getTestData("product2");

    @Test
    @WithToken
    @Owner("Дарья Петрова")
    @Feature("Реализация списка избранных товаров пользователя")
    @Story("API: Получение списка избранных товаров")
    @DisplayName("Проверка получения списка товаров из избранного")
    public void checkListOfFavoritesGettingApiTest() {
        steps.addProductToFavorites(product);
        steps.addProductToFavorites(product2);
        steps.checkProductsAreInFavorites(product, product2);

        steps.removeFromFavorites(product);
        steps.removeFromFavorites(product2);
    }

    @Test
    @WithToken
    @Owner("Дарья Петрова")
    @Feature("Реализация списка избранных товаров пользователя")
    @Story("API: Получение списка избранных товаров")
    @DisplayName("Проверка получения пустого списка товаров из избранного")
    public void checkEmptyListOfFavoritesGettingApiTest() {
        steps.checkFavoritesListIsEmpty();
    }

    @Test
    @WithToken
    @Owner("Дарья Петрова")
    @Feature("Реализация списка избранных товаров пользователя")
    @Story("API: Получение списка избранных товаров")
    @DisplayName("Проверка получения списка товаров из избранного без авторизации")
    public void checkListOfFavoritesGettingWithoutCookieApiTest() {
        steps.checkFavoritesListNoSessionGuid();
    }
}
