package tests.api;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import common.TestData;

@DisplayName("API тесты на список товаров в избранном")
@Tag("API")
public class FavouritesListApiTests extends TestBaseApi {

    TestSteps steps = new TestSteps();

    @Test
    @Owner("Дарья Петрова")
    @Feature("Реализация списка избранных товаров пользователя")
    @Story("API: Получение списка избранных товаров")
    @DisplayName("Проверка получения списка товаров из избранного")
    public void getListOfFavouritesApi() {
        steps.addProductToFavourites(TestData.product);
        steps.addProductToFavourites(TestData.product2);
        steps.checkProductsAreInFavourites(TestData.product, TestData.product2);

        steps.removeFromFavourites(TestData.product);
        steps.removeFromFavourites(TestData.product2);
    }

    @Test
    @Owner("Дарья Петрова")
    @Feature("Реализация списка избранных товаров пользователя")
    @Story("API: Получение списка избранных товаров")
    @DisplayName("Проверка получения пустого списка товаров из избранного")
    public void getEmptyListOfFavouritesApi() {
        steps.checkFavouritesListIsEmpty();
    }

    @Test
    @Owner("Дарья Петрова")
    @Feature("Реализация списка избранных товаров пользователя")
    @Story("API: Получение списка избранных товаров")
    @DisplayName("Проверка получения списка товаров из избранного без авторизации")
    public void getListOfFavouritesWithoutCookieApi() {
        steps.checkFavouritesListNoSessionGuid();
    }
}
