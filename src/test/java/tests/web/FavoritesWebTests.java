package tests.web;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import web.pages.ProductPage;

import static common.TestData.getTestData;

@DisplayName("UI тесты на избранное")
@Tag("WEB")
public class FavoritesWebTests extends TestBaseWeb {

    final ProductPage productPage = new ProductPage();
    final String product = getTestData("product");

    @Test
    @Owner("Дарья Петрова")
    @Feature("Реализация списка избранных товаров пользователя")
    @Story("UI: Добавление товара в список избранного")
    @DisplayName("Проверка добавления товара в избранное")
    public void checkProductAddingToFavoritesTest() {
        productPage
                .openPage(product)
                .addProductToFavorites()
                .openFavorites()
                .checkAddedProductIsInFavorites(product);
    }

    @Test
    @Owner("Дарья Петрова")
    @Feature("Реализация списка избранных товаров пользователя")
    @Story("UI: Удаление товара из списка избранного")
    @DisplayName("Проверка удаления продукта из избранного")
    public void checkProductDeletingFromFavoritesTest() {
        productPage
                .openPage(product)
                .addProductToFavorites()
                .openFavorites()
                .removeProductFromFavorites(product)
                .checkFavoritesListIsEmpty();
    }
}
