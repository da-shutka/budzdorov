package tests.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import pages.ProductPage;

@DisplayName("Тесты на Избранное")
@Tags({@Tag("WEB"), @Tag("FAV")})
public class FavouritesWebTests extends TestBaseWeb {

    private static final String product = "34225";
    ProductPage productPage = new ProductPage();

    @Test
    @DisplayName("Добавление продукта в Избранное")
    public void addProductToFavourites() {
        productPage
                .openPage(product)
                .addProductToFavourites()
                .checkAddedProductIsInFavourites(product);
    }

    @Test
    @DisplayName("Удаление продукта из Избранного")
    public void removeProductFromFavourites() {
        productPage
                .openPage(product)
                .addProductToFavourites()
                .removeProductFromFavourites(product)
                .checkFavouritesIsEmpty();
    }
}
