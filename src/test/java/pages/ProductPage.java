package pages;

import auth.AuthorizationWeb;
import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.actions;

public class ProductPage {

    private final SelenideElement
            advPopup = $(".popup-metadata-popup__paranja"),
            cityPopup = $("div.city-confirm"),
            favIcon = $("div.wishlist-icon"),
            favProduct = $("a.wish-list__list-item-info-text"),
            favList = $("div.wish-list__products-count"),
            addToCartButton = $(".product-add-to-cart__btn"),
            //cart = $("$(a[href='/cart'])");
            cart = $(".minicart-top__link"),
            plusButton = $$("button.product-change-qty__btn").get(1);

    @Step("Открыть страницу товара {productId}")
    public ProductPage openPage(String productId) {
        open("/product/" + productId);
        waitAndRemovePopups();
        return this;
    }

    @Step("Дождаться загрузки всех попапов и удалить их")
    public void waitAndRemovePopups() {
        sleep(2000);
        executeJavaScript("arguments[0].remove();", advPopup);
        executeJavaScript("arguments[0].remove();", cityPopup);
    }

    @Step("Добавить товр в Избранное")
    public ProductPage addProductToFavourites() {
        $("button.wishlist-product-control.product-content-basket__button-like").click();
        return this;
    }

    @Step("Проверить, что товар {productId} добавлен в Избранное")
    public void checkAddedProductIsInFavourites(String productId) {
        executeJavaScript("arguments[0].click();", favIcon);
        $("a.wish-list__list-item-info-text[href*='/product/" + productId + "']").should(exist);
    }

    @Step("Удалить товар {productId} из Избранного")
    public ProductPage removeProductFromFavourites(String productId) {
        executeJavaScript("arguments[0].click();", favIcon);
        $("a.wish-list__list-item-info-text[href*='/product/" + productId + "']")
                .parent()
                .sibling(0)
                .$("use").click();
        return this;
    }

    @Step("Проверить, что список в Избранном пуст")
    public void checkFavouritesIsEmpty() {
        favList.shouldHave(text("Избранных товаров нет"));
    }

    @Step("Добавить товар в корзину")
    public ProductPage addProductToCart() {
        addToCartButton.click();
        return this;
    }

    @Step("Открыть корзину")
    public void openCart() {
        sleep(3000);
        executeJavaScript("arguments[0].click();", cart);
    }

    @Step("Нажать на + для добавления товара в корзину")
    public ProductPage addMultipleQtyProductToCart() {
        executeJavaScript("arguments[0].click();", plusButton);
        return this;
    }

}
