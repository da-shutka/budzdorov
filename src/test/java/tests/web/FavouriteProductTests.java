package tests.web;

import helpers.WithLogin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FavouriteProductTests {

    @Test
    @WithLogin
    @DisplayName("Добавление продукта в Избранное")
    public void addProductToFavourites(){
        ProductPage
                .openPage()
                .addProductToFavourites();
        favourites.checkAddedProductIsInFavourites();
        product.removeFromFavourites();
    }

    @Test
    @WithLogin
    @DisplayName("Добавление нескольких продуктов в Избранное")
    public void addMultipleProductsToFavourites(){
        ProductPage
                .openPage()
                .addProductToFavourites()
                .addProductToFavourites();
        favourites.checkAddedProductIsInFavourites();
        favourites.checkAddedProductIsInFavourites();
        product.removeFromFavourites();
        product.removeFromFavourites();
    }

    @Test
    @WithLogin
    @DisplayName("Удаление продукта из Избранного")
    public void removeProductFromFavourites(){
        product.addProductToFavourites();
        AccountPage
                .openPage()
                .removeProductIsInFavourites()
                .checkNoRemovedProductIsInFavourites();
    }


}
