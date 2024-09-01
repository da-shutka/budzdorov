package web.pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ProductPage {

    private final SelenideElement
            productIdOnPage = $(".product-cart__toolbar-code-number"),
            dataServerRendered = $("[id='app'].app._show-top-bar:not([data-server-rendered='true'])"),
            favIcon = $("div.wishlist-icon"),
            favButton = $("button.wishlist-product-control.product-content-basket__button-like"),
            favList = $("div.wish-list__products-count"),
            addToCartButton = $(".product-add-to-cart__btn"),
            cart = $(".minicart-top__link"),
            plusButton = $$("button.product-change-qty__btn").get(1),
            cartCount = $(".minicart-top__link .counter__number");


    @Step("Открыть страницу товара '{productId}' по ссылке https://www.rigla.ru/product/{productId}")
    public ProductPage openPage(String productId) {
        open("/product/" + productId);
        productIdOnPage.shouldHave(text(productId));
        Configuration.timeout = 30000;
        dataServerRendered.should(exist);
        Configuration.timeout = 10000;
        return this;
    }

    @Step("Нажать на сердечко у товара, чтобы добавить товар в Избранное")
    public ProductPage addProductToFavourites() {
        actions().moveToElement(favButton).build().perform();
        favButton.click();
        return this;
    }

    @Step("Нажать на сердечко, чтобы открыть список избранных товаров")
    public ProductPage openFavourites() {
        executeJavaScript("window.scrollTo(0, 0);");
        executeJavaScript("arguments[0].click();", favIcon);
        return this;
    }

    @Step("Проверить, что товар '{productId}' добавлен в Избранное")
    public void checkAddedProductIsInFavourites(String productId) {
        $("a.wish-list__list-item-info-text[href*='/product/" + productId + "']").should(exist);
    }

    @Step("Нажать X у товара '{productId}', чтобы удалить из Избранного")
    public ProductPage removeProductFromFavourites(String productId) {
        $("a.wish-list__list-item-info-text[href*='/product/" + productId + "']")
                .parent()
                .sibling(0)
                .$("use").click();
        return this;
    }

    @Step("Проверить, что список избранных товаров пуст")
    public void checkFavouritesListIsEmpty() {
        favList.shouldHave(text("Избранных товаров нет"));
    }

    @Step("Нажать кнопку 'В корзину'")
    public ProductPage addProductToCart(int count) {
        addToCartButton.click();
        cartCount.shouldHave(attribute("innerText", " " + count + " "));
        return this;
    }

    @Step("Нажать значок корзины")
    public void openCart() {
        executeJavaScript("window.scrollTo(0, 0);");
        executeJavaScript("arguments[0].click();", cart);
    }

    @Step("Нажать на + для добавления товара в корзину")
    public ProductPage addMultipleQtyProductToCart(int count) {
        actions().moveToElement(plusButton).build().perform();
        executeJavaScript("arguments[0].click();", plusButton);
        cartCount.shouldHave(attribute("innerText", " " + count + " "));
        return this;
    }
}
