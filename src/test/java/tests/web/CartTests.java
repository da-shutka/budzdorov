package tests.web;

import helpers.WithLogin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CartTests {

    @Test
    @WithLogin
    @DisplayName("Добавление продукта в корзину")
    public void addProductToCart(){
        ProductPage
                .openPage()
                .addProductToCart();
        cart.checkAddedProductIsInCart();
        cart.removeProduct();
    }

    @Test
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
    }

    @Test
    @WithLogin
    @DisplayName("Добавление нескольких одинаковых продуктов в корзину")
    public void addTheSameProductsToCart(){
        ProductPage
                .openPage()
                .addProductToCart()
                .addProductToCart();
        cart.checkAddedProductIsInCart();
        cart.checkCountOfProduct();
        cart.removeProduct();
    }

    @Test
    @WithLogin
    @DisplayName("Увеличение количества продукта в корзине")
    public void increaseProductCountInCart(){
        cart.addProduct();
        CartPage
                .openPage()
                .increaseProductCount()
                .checkProductCount();
        cart.removeProduct();
    }

    @Test
    @WithLogin
    @DisplayName("Уменьшение количества продукта в корзине")
    public void decreaseProductCountInCart(){
        cart.addProduct();
        cart.addProduct();
        CartPage
                .openPage()
                .decreaseProductCount()
                .checkProductCount();
        cart.removeProduct();
    }

    @Test
    @WithLogin
    @DisplayName("Удаление продукта из корзины")
    public void removeProductFromCart(){
        cart.addProduct();
        cart.addProduct();
        CartPage
                .openPage()
                .removeProduct()
                .checkProductIsRemoved();
    }

    @Test
    @WithLogin
    @DisplayName("Очистка корзины")
    public void clearCart(){
        cart.addProduct();
        cart.addProduct();
        CartPage
                .openPage()
                .clearCart()
                .checkCartIsEmpty();
    }
}
