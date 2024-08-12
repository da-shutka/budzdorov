package tests.api;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import tests.TestData;

@DisplayName("API тесты на список товаров в избранном")
@Tags({
        @Tag("API"),
        @Tag("FAV"),
        @Tag("FAV_LIST")
})
public class FavouritesListApiTests extends TestBaseApi {

    TestSteps steps = new TestSteps();

    @Test
    @Owner("Дарья Петрова")
    @Story("Реализация списка избранных товаров пользователя")
    @Feature("Получение списка избранных товаров")
    @DisplayName("Получение списка товаров из избранного")
    public void getListOfFavouritesApi() {
        steps.addProductToFavourites(TestData.product);
        steps.addProductToFavourites(TestData.product2);
        steps.checkProductsAreInFavourites(TestData.product, TestData.product2);

        steps.removeFromFavourites(TestData.product);
        steps.removeFromFavourites(TestData.product2);
    }

    @Test
    @Owner("Дарья Петрова")
    @Story("Реализация списка избранных товаров пользователя")
    @Feature("Получение списка избранных товаров")
    @DisplayName("Получение пустого списка товаров из избранного")
    public void getEmptyListOfFavouritesApi() {
        steps.checkFavouritesListIsEmpty();
    }

    @Test
    @Owner("Дарья Петрова")
    @Story("Реализация списка избранных товаров пользователя")
    @Feature("Получение списка избранных товаров")
    @DisplayName("Получение списка товаров из избранного без авторизации")
    public void getListOfFavouritesWithoutCookieApi() {
        steps.checkFavouritesListNoSessionGuid();
    }
}
