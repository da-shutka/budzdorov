package tests.web;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import pages.CartPage;
import pages.ProductPage;
import tests.TestData;

@DisplayName("UI тесты на корзину")
@Tags({
        @Tag("WEB"),
        @Tag("CART")
})
public class CartWebTests extends TestBaseWeb {

    ProductPage productPage = new ProductPage();
    CartPage cartPage = new CartPage();

    @Test
    @Owner("Дарья Петрова")
    @Story("Реализация корзины пользователя с товарами")
    @Feature("Добавление товара в корзину")
    @DisplayName("Добавление товара в корзину")
    public void addProductToCart() {
        productPage
                .openPage(TestData.product)
                .addProductToCart()
                .openCart();
        cartPage.checkAddedProductIsInCart(TestData.product);
    }

    @Test
    @Owner("Дарья Петрова")
    @Story("Реализация корзины пользователя с товарами")
    @Feature("Добавление товара в корзину")
    @DisplayName("Добавление двух одинаковых товаров в корзину через +")
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
    @Story("Реализация корзины пользователя с товарами")
    @Feature("Изменение товара в корзине")
    @DisplayName("Увеличение количества продукта в корзине")
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
    @Story("Реализация корзины пользователя с товарами")
    @Feature("Изменение товара в корзине")
    @DisplayName("Уменьшение количества продукта в корзине")
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
    @Story("Реализация корзины пользователя с товарами")
    @Feature("Удаление товара из корзины")
    @DisplayName("Удаление продукта из корзины")
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
    @Story("Реализация корзины пользователя с товарами")
    @Feature("Удаление товара из корзины")
    @DisplayName("Очистка корзины")
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