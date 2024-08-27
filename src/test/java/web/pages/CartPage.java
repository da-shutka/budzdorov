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
            deleteProductButton = $(".cart-row__delete-icon"),
            clearCartButton = $(".cart-title-close"),
            cartTitleEmpty = $(".cart-empty");

    @Step("Проверить, что добавленный товар '{productId}' в корзине")
    public CartPage checkAddedProductIsInCart(String productId) {
        $("a.product-info-row__title[href*='/product/" + productId + "']").should(exist);
        return this;
    }

    @Step("Проверить, что в заголовке над корзиной количество товара - {qty}")
    public void checkProductQty(Integer qty) {
        String product = "";
        if (qty == 1) {
            product = "товар";
        } else {
            product = "товара";
        }
        ;
        sleep(3000);
        cartProductTitle.shouldHave(text(qty + " " + product));
        productQty.shouldHave(attribute("_value", qty.toString()));
    }

    @Step("Нажать на +, чтобы увеличить количество товара в корзине")
    public CartPage increaseProductCount() {
        executeJavaScript("arguments[0].click();", plusButton);
        return this;
    }

    @Step("Нажать на -, чтобы уменьшить количество товара в корзине")
    public CartPage decreaseProductCount() {
        executeJavaScript("arguments[0].click();", minusButton);
        return this;
    }

    @Step("Нажать на X у товара, чтобы удалить товар из корзины")
    public CartPage deleteProductFromCart() {
        actions().moveToElement(deleteProductButton).clickAndHold().release().perform();
        return this;
    }

    @Step("Проверить, что корзина пуста")
    public void checkCartIsEmpty() {
        sleep(3000);
        cartTitleEmpty.shouldHave(text("Корзина пустая"));
    }

    @Step("Нажать 'Очистить корзину'")
    public CartPage clearCart() {
        clearCartButton.click();
        return this;
    }
}
