package tests.web;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import web.pages.CartPage;
import web.pages.ProductPage;
import common.TestData;

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
    public void addProductToCart() {
        productPage
                .openPage(TestData.product)
                .addProductToCart()
                .openCart();
        cartPage.checkAddedProductIsInCart(TestData.product);
    }

    @Test
    @Owner("Дарья Петрова")
    @Feature("Реализация корзины пользователя с товарами")
    @Story("UI: Добавление товара в корзину")
    @DisplayName("Проверка добавления двух одинаковых товаров в корзину через +")
    public void addTheSameProductsToCart() {
        productPage
                .openPage(TestData.product)
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
    public void increaseProductCountInCart() {
        productPage
                .openPage(TestData.product)
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
    public void decreaseProductCountInCart() {
        productPage
                .openPage(TestData.product)
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
    public void removeProductFromCart() {
        productPage
                .openPage(TestData.product)
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
    public void clearCart() {
        productPage
                .openPage(TestData.product)
                .addProductToCart()
                .openPage(TestData.product2)
                .addProductToCart()
                .openCart();
        cartPage
                .clearCart()
                .checkCartIsEmpty();
    }
}