package tests.web;

import helpers.WithLogin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.CartPage;
import pages.ProductPage;

import static com.codeborne.selenide.Selenide.sleep;

@DisplayName("Тесты на Корзину")
public class CartTests extends TestBase {

    ProductPage productPage = new ProductPage();
    CartPage cartPage = new CartPage();
    private static final String
            product = "34225",
            product2 = "34226";

    @Test
    @DisplayName("Добавление товара в корзину")
    public void addProductToCart() {
        productPage
                .openPage(product)
                .addProductToCart()
                .openCart();
        cartPage.checkAddedProductIsInCart(product);
    }

    /*@Test
    @WithLogin
    @DisplayName("Добавление нескольких разных продуктов в корзину")
    public void addMultipleProductsToCart(){
        ProductPage
                .openPage()
                .addProductToCart();
        ProductPage
                .openPage()
                .addProductToCart();
        cart.checkAddedProductIsInCart();
        cart.checkAddedProductIsInCart();
        cart.removeProduct();
        cart.removeProduct();
    }*/

    @Test
    @DisplayName("Добавление двух одинаковых товаров в корзину через +")
    public void addTheSameProductsToCart() {
        productPage
                .openPage(product)
                .addMultipleQtyProductToCart()
                .openCart();
        cartPage
                .checkAddedProductIsInCart(product)
                .checkProductQty(2);
    }

    @Test
    @DisplayName("Увеличение количества продукта в корзине")
    public void increaseProductCountInCart() {
        productPage
                .openPage(product)
                .addProductToCart()
                .openCart();
        cartPage
                .increaseProductCount()
                .checkProductQty(2);
    }

    @Test
    @DisplayName("Уменьшение количества продукта в корзине")
    public void decreaseProductCountInCart() {
        productPage
                .openPage(product)
                .addMultipleQtyProductToCart()
                .openCart();
        cartPage
                .decreaseProductCount()
                .checkProductQty(1);
    }

    @Test
    @DisplayName("Удаление продукта из корзины")
    public void removeProductFromCart() {
        productPage
                .openPage(product)
                .addProductToCart()
                .openCart();
        cartPage
                .deleteProductFromCart()
                .checkCartIsEmpty();
    }

    @Test
    @DisplayName("Очистка корзины")
    public void clearCart() {
        productPage
                .openPage(product)
                .addProductToCart()
                .openPage(product2)
                .addProductToCart()
                .openCart();
        cartPage
                .clearCart()
                .checkCartIsEmpty();
    }
}
