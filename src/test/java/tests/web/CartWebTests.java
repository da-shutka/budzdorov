package tests.web;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import web.pages.CartPage;
import web.pages.ProductPage;

import static common.TestData.getTestData;

@DisplayName("UI тесты на корзину")
@Tag("WEB")
public class CartWebTests extends TestBaseWeb {

    final ProductPage productPage = new ProductPage();
    final CartPage cartPage = new CartPage();
    String product = getTestData("product");
    String product2 = getTestData("product2");

    @Test
    @Owner("Дарья Петрова")
    @Feature("Реализация корзины пользователя с товарами")
    @Story("UI: Добавление товара в корзину")
    @DisplayName("Проверка добавления товара в корзину")
    public void checkProductAddingToCartTest() {
        productPage
                .openPage(product)
                .addProductToCart(1)
                .openCart();
        cartPage.checkAddedProductIsInCart(product);
    }

    @Test
    @Owner("Дарья Петрова")
    @Feature("Реализация корзины пользователя с товарами")
    @Story("UI: Добавление товара в корзину")
    @DisplayName("Проверка добавления двух одинаковых товаров в корзину через +")
    public void checkTheSameProductsAddingToCartTest() {
        productPage
                .openPage(product)
                .addMultipleQtyProductToCart(2)
                .openCart();
        cartPage
                .checkAddedProductIsInCart(product)
                .checkProductQty(2);
    }

    @Test
    @Owner("Дарья Петрова")
    @Feature("Реализация корзины пользователя с товарами")
    @Story("UI: Изменение товара в корзине")
    @DisplayName("Проверка увеличения количества товара в корзине")
    public void checkProductCountIncreasingInCartTest() {
        productPage
                .openPage(product)
                .addProductToCart(1)
                .openCart();
        cartPage
                .increaseProductCount()
                .checkProductQty(2);
    }

    @Test
    @Owner("Дарья Петрова")
    @Feature("Реализация корзины пользователя с товарами")
    @Story("UI: Изменение товара в корзине")
    @DisplayName("Проверка уменьшения количества товара в корзине")
    public void checkProductCountDecreasingInCartTest() {
        productPage
                .openPage(product)
                .addMultipleQtyProductToCart(2)
                .openCart();
        cartPage
                .decreaseProductCount()
                .checkProductQty(1);
    }

    @Test
    @Owner("Дарья Петрова")
    @Feature("Реализация корзины пользователя с товарами")
    @Story("UI: Удаление товара из корзины")
    @DisplayName("Проверка удаления товара из корзины")
    public void checkProductDeletingFromCartTest() {
        productPage
                .openPage(product)
                .addProductToCart(1)
                .openCart();
        cartPage
                .deleteProductFromCart()
                .checkCartIsEmpty();
    }

    @Test
    @Owner("Дарья Петрова")
    @Feature("Реализация корзины пользователя с товарами")
    @Story("UI: Удаление товара из корзины")
    @DisplayName("Проверка очистки корзины")
    public void checkCartClearingTest() {
        productPage
                .openPage(product)
                .addProductToCart(1);
        productPage
                .openPage(product2)
                .addProductToCart(2)
                .openCart();
        cartPage
                .clearCart()
                .checkCartIsEmpty();
    }
}