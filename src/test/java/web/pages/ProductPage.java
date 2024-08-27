package web.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import web.pages.components.PopupComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class ProductPage {

    private final SelenideElement
            productIdOnPage = $(".product-cart__toolbar-code-number"),
            favIcon = $("div.wishlist-icon"),
            favButton = $("button.wishlist-product-control.product-content-basket__button-like"),
            favList = $("div.wish-list__products-count"),
            addToCartButton = $(".product-add-to-cart__btn"),
            cart = $(".minicart-top__link"),
            plusButton = $$("button.product-change-qty__btn").get(1);

    PopupComponent popup = new PopupComponent();

    @Step("Открыть страницу товара '{productId}' по ссылке https://www.rigla.ru/product/{productId}")
    public ProductPage openPage(String productId) {
        open("/product/" + productId);
        productIdOnPage.shouldHave(text(productId), Duration.ofSeconds(10));
        return this;
    }

    @Step("Дождаться загрузки всех попапов и удалить их")
    public ProductPage waitAndRemovePopups() {
        popup.waitAndRemovePopups();
        return this;
    }

    @Step("Нажать на сердечко у товара, чтобы добавить товар в Избранное")
    public ProductPage addProductToFavourites() {
        favButton.scrollTo().click();
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
    public void checkFavouritesIsEmpty() {
        favList.shouldHave(text("Избранных товаров нет"));
    }

    @Step("Нажать кнопку 'В корзину'")
    public ProductPage addProductToCart() {
        addToCartButton.scrollTo().click();
        return this;
    }

    @Step("Нажать значок корзины")
    public void openCart() {
        sleep(5000);
        executeJavaScript("window.scrollTo(0, 0);");
        executeJavaScript("arguments[0].click();", cart);
    }

    @Step("Нажать на + для добавления товара в корзину")
    public ProductPage addMultipleQtyProductToCart() {
        plusButton.scrollTo();
        executeJavaScript("arguments[0].click();", plusButton);
        return this;
    }
}
