package tests.api;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import tests.TestData;

@DisplayName("API тесты на добавление товара в избранное")
@Tags({
        @Tag("API"),
        @Tag("FAV"),
        @Tag("FAV_ADD")
})
public class AddToFavouritesApiTests extends TestBaseApi {

    TestSteps steps = new TestSteps();

    @Test
    @Owner("Дарья Петрова")
    @DisplayName("Добавление товара в избранное")
    public void addProductToFavouritesApi() {
        steps.addProductToFavourites(TestData.product);
        steps.checkProductIsInFavourites(TestData.product);

        steps.removeFromFavourites(TestData.product);
    }

    @Test
    @Owner("Дарья Петрова")
    @DisplayName("Добавление несуществующего товара в избранное")
    public void addNonexistingProductToFavouritesApi() {
        steps.addProductToFavourites(TestData.unknownProduct);
        steps.checkFavouritesListIsEmpty();
    }

    @Test
    @Owner("Дарья Петрова")
    @DisplayName("Добавление товара в избранное дважды")
    public void addProductToFavouritesTwiceApi() {
        steps.addProductToFavourites(TestData.product);
        steps.addProductToFavourites(TestData.product);
        steps.checkProductIsInFavourites(TestData.product);

        steps.removeFromFavourites(TestData.product);
    }

    @Test
    @Owner("Дарья Петрова")
    @DisplayName("Добавление товара в избранное без авторизации")
    public void addProductToFavouritesWithoutCookieApi() {
        steps.addProductToFavouritesNoSessionGuid(TestData.product);
    }
}