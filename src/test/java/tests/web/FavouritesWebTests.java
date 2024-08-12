package tests.web;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import pages.ProductPage;
import tests.TestData;

@DisplayName("UI тесты на избранное")
@Tags({
        @Tag("WEB"),
        @Tag("FAV")
})
public class FavouritesWebTests extends TestBaseWeb {

    ProductPage productPage = new ProductPage();

    @Test
    @Owner("Дарья Петрова")
    @Story("Реализация списка избранных товаров пользователя")
    @Feature("Добавление товара в список избранного")
    @DisplayName("Добавление продукта в Избранное")
    public void addProductToFavourites() {
        productPage
                .openPage(TestData.product)
                .addProductToFavourites()
                .checkAddedProductIsInFavourites(TestData.product);
    }

    @Test
    @Owner("Дарья Петрова")
    @Story("Реализация списка избранных товаров пользователя")
    @Feature("Удаление товара из списка избранного")
    @DisplayName("Удаление продукта из Избранного")
    public void removeProductFromFavourites() {
        productPage
                .openPage(TestData.product)
                .addProductToFavourites()
                .removeProductFromFavourites(TestData.product)
                .checkFavouritesIsEmpty();
    }
}
