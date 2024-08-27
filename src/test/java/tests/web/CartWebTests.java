package tests.web;

import common.TestData;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import web.pages.CartPage;
import web.pages.ProductPage;

@DisplayName("UI тесты на корзину")
@Tag("WEB")
public class CartWebTests extends TestBaseWeb {

    ProductPage productPage = new ProductPage();
    CartPage cartPage = new CartPage();

    @Test
    @Owner("Дарья Петрова")
    @Feature("Реализация корзины пользователя с товарами")
    @Story("UI: Добавление товара в корзину")
    @DisplayName("Проверка добавления товара в корзину")
    public void checkProductAddingToCartTest() {
        productPage
                .openPage(TestData.product)
                .waitAndRemovePopups()
                .addProductToCart()
                .openCart();
        cartPage.checkAddedProductIsInCart(TestData.product);
    }

    @Test
    @Owner("Дарья Петрова")
    @Feature("Реализация корзины пользователя с товарами")
    @Story("UI: Добавление товара в корзину")
    @DisplayName("Проверка добавления двух одинаковых товаров в корзину через +")
    public void checkTheSameProductsAddingToCartTest() {
        productPage
                .openPage(TestData.product)
                .waitAndRemovePopups()
                .addMultipleQtyProductToCart()
                .openCart();
        cartPage
                .checkAddedProductIsInCart(TestData.product)
                .checkProductQty(2);
    }

    @Test
    @Owner("Дарья Петрова")
    @Feature("Реализация корзины пользователя с товарами")
    @Story("UI: Изменение товара в корзине")
    @DisplayName("Проверка увеличения количества товара в корзине")
    public void checkProductCountIncreasingInCartTest() {
        productPage
                .openPage(TestData.product)
                .waitAndRemovePopups()
                .addProductToCart()
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
                .openPage(TestData.product)
                .waitAndRemovePopups()
                .addMultipleQtyProductToCart()
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
                .openPage(TestData.product)
                .waitAndRemovePopups()
                .addProductToCart()
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
                .openPage(TestData.product)
                .waitAndRemovePopups()
                .addProductToCart();
        productPage
                .openPage(TestData.product2)
                .waitAndRemovePopups()
                .addProductToCart()
                .openCart();
        cartPage
                .clearCart()
                .checkCartIsEmpty();
    }
}