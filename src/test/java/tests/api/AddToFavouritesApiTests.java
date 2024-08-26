package tests.api;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import common.TestData;

@DisplayName("API тесты на добавление товара в избранное")
@Tag("API")
public class AddToFavouritesApiTests extends TestBaseApi {

    TestSteps steps = new TestSteps();

    @Test
    @Owner("Дарья Петрова")
    @Feature("Реализация списка избранных товаров пользователя")
    @Story("API: Добавление товара в список избранного")
    @DisplayName("Проверка добавления товара в избранное")
    public void addProductToFavouritesApi() {
        steps.addProductToFavourites(TestData.product);
        steps.checkProductIsInFavourites(TestData.product);
        steps.removeFromFavourites(TestData.product);
    }

    @Test
    @Owner("Дарья Петрова")
    @Feature("Реализация списка избранных товаров пользователя")
    @Story("API: Добавление товара в список избранного")
    @DisplayName("Проверка добавления несуществующего товара в избранное")
    public void addNonexistingProductToFavouritesApi() {
        steps.addProductToFavourites(TestData.unknownProduct);
        steps.checkFavouritesListIsEmpty();
    }

    @Test
    @Owner("Дарья Петрова")
    @Feature("Реализация списка избранных товаров пользователя")
    @Story("API: Добавление товара в список избранного")
    @DisplayName("Проверка добавления товара в избранное дважды")
    public void addProductToFavouritesTwiceApi() {
        steps.addProductToFavourites(TestData.product);
        steps.addProductToFavourites(TestData.product);
        steps.checkProductIsInFavourites(TestData.product);

        steps.removeFromFavourites(TestData.product);
    }

    @Test
    @Owner("Дарья Петрова")
    @Feature("Реализация списка избранных товаров пользователя")
    @Story("API: Добавление товара в список избранного")
    @DisplayName("Проверка добавления товара в избранное без авторизации")
    public void addProductToFavouritesWithoutCookieApi() {
        steps.checkAddProductToFavouritesNoSessionGuid(TestData.product);
    }
}
