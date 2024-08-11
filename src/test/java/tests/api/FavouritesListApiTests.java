package tests.api;

import io.qameta.allure.Owner;
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
    @DisplayName("Получение пустого списка товаров из избранного")
    public void getEmptyListOfFavouritesApi() {
        steps.checkFavouritesListIsEmpty();
    }

    @Test
    @Owner("Дарья Петрова")
    @DisplayName("Получение списка товаров из избранного без авторизации")
    public void getListOfFavouritesWithoutCookieApi() {
        steps.checkFavouritesListNoSessionGuid();
    }
}
