package web.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CartPage {

    private final SelenideElement
            cartProductTitle = $(".cart__title-description"),
            productQty = $(".product-change-qty__qty"),
            plusButton = $$("button.product-change-qty__btn").get(1),
            minusButton = $$("button.product-change-qty__btn").first(),
            deleteProductButton = $(".cart-row__delete use"),
            clearCartButton = $(".cart__delete-icon use"),
            cartTitleEmpty = $(".cart-empty");

    @Step("Проверить, что добавленный товар в корзине")
    public CartPage checkAddedProductIsInCart(String productId) {
        $("a.product-info-row__title[href*='/product/" + productId + "']").should(exist);
        return this;
    }

    @Step("Проверить, что количество товара - {qty}")
    public void checkProductQty(Integer qty) {
        String product = "";
        if (qty == 1) {
            product = "товар";
        } else if (qty >= 2 && qty <= 5) {
            product = "товара";
        } else product = "товаров";

        cartProductTitle.shouldHave(text(qty + " " + product));
        productQty.shouldHave(attribute("_value", qty.toString()));
    }

    @Step("Увеличить количество товара в корзине")
    public CartPage increaseProductCount() {
        executeJavaScript("arguments[0].click();", plusButton);
        return this;
    }

    @Step("Уменьшить количество товара в корзине")
    public CartPage decreaseProductCount() {
        executeJavaScript("arguments[0].click();", minusButton);
        return this;
    }

    @Step("Удалить товар из корзины")
    public CartPage deleteProductFromCart() {
        actions().moveToElement(deleteProductButton).clickAndHold().release().perform();
        return this;
    }

    @Step("Проверить, что корзина пуста")
    public void checkCartIsEmpty() {
        sleep(3000);
        cartTitleEmpty.shouldHave(text("Корзина пустая"));
    }

    @Step("Очистить корзину")
    public CartPage clearCart() {
        actions().moveToElement(clearCartButton).clickAndHold().release().perform();
        return this;
    }
}
