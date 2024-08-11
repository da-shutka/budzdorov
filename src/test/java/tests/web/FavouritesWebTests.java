package tests.web;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import pages.ProductPage;
import tests.TestData;

@DisplayName("Тесты на Избранное")
@Tags({
        @Tag("WEB"),
        @Tag("FAV")
})
public class FavouritesWebTests extends TestBaseWeb {

    ProductPage productPage = new ProductPage();

    @Test
    @Owner("@petrova_di")
    @DisplayName("Добавление продукта в Избранное")
    public void addProductToFavourites() {
        productPage
                .openPage(TestData.product)
                .addProductToFavourites()
                .checkAddedProductIsInFavourites(TestData.product);
    }

    @Test
    @Owner("@petrova_di")
    @DisplayName("Удаление продукта из Избранного")
    public void removeProductFromFavourites() {
        productPage
                .openPage(TestData.product)
                .addProductToFavourites()
                .removeProductFromFavourites(TestData.product)
                .checkFavouritesIsEmpty();
    }
}
