package tests.web;

import api.FavouritesApi;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.ProductPage;

@DisplayName("Тесты на Избранное")
public class FavouritesTests extends TestBase {

    ProductPage productPage = new ProductPage();
    //FavouritesApi favourites = new FavouritesApi();

    @Test
    @DisplayName("Добавление продукта в Избранное")
    public void addProductToFavourites() {
        String product = "34225";
        productPage
                .openPage(product)
                .addProductToFavourites()
                .checkAddedProductIsInFavourites(product);

        /*AuthorizationWeb.setCookiesForApi();
        favourites.removeFromFavourites(product);*/
    }

    @Test
    @DisplayName("Удаление продукта из Избранного")
    public void removeProductFromFavourites() {
        String product = "34225";
        productPage
                .openPage(product)
                .addProductToFavourites()
                .removeProductFromFavourites(product)
                .checkFavouritesIsEmpty();
    }


}
