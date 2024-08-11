package tests.web;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import pages.CartPage;
import pages.ProductPage;
import tests.TestData;

@DisplayName("Тесты на Корзину")
@Tags({
        @Tag("WEB"),
        @Tag("CART")
})
public class CartWebTests extends TestBaseWeb {

    ProductPage productPage = new ProductPage();
    CartPage cartPage = new CartPage();

    @Test
    @Owner("@petrova_di")
    @DisplayName("Добавление товара в корзину")
    public void addProductToCart() {
        productPage
                .openPage(TestData.product)
                .addProductToCart()
                .openCart();
        cartPage.checkAddedProductIsInCart(TestData.product);
    }

    @Test
    @Owner("@petrova_di")
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
    @Owner("@petrova_di")
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
    @Owner("@petrova_di")
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
    @Owner("@petrova_di")
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
    @Owner("@petrova_di")
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