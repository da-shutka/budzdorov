package tests.api;

import api.auth.AuthorizationWeb;
import api.models.AccessDeniedModel;
import api.models.FavouritesModel;
import api.models.ItemModel;
import api.models.NotFoundModel;
import io.qameta.allure.Step;

import static api.specs.RiglaSpec.*;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestSteps {

    @Step("Добавить товар '{productId}' в избранное")
    public void addProductToFavourites(String productId) {
        ItemModel productItem = new ItemModel();
        productItem.setSku(productId);

        FavouritesModel productData = new FavouritesModel();
        productData.setItems(new ItemModel[]{productItem});

        given(requestSpec)
                .cookies("RIGLA_WEB_SESSION_GUID", AuthorizationWeb.sessionGuidForApi)
                .body(productData)
                .when()
                .post("/V1/wishlist/items")
                .then()
                .spec(responseSpecWithStatusCode200);
    }

    @Step("Проверить, что товар '{productId}' добавлен в избранное и его количество равно 1")
    public void checkProductIsInFavourites(String productId) {
        FavouritesModel response = given(requestSpec)
                .cookies("RIGLA_WEB_SESSION_GUID", AuthorizationWeb.sessionGuidForApi)
                .when()
                .get("/V1/wishlist/items")
                .then()
                .spec(responseSpecWithStatusCode200)
                .extract().as(FavouritesModel.class);

        assertThat(response.getItems().length).isEqualTo(1);
        assertThat(response.getItems()).extracting(ItemModel::getSku).first().isEqualTo(productId);
        assertThat(response.getItems()).extracting(ItemModel::getQty).first().isEqualTo("1");
    }

    @Step("Проверить, что список избранных товаров пуст")
    public void checkFavouritesListIsEmpty() {
        FavouritesModel response = given(requestSpec)
                .cookies("RIGLA_WEB_SESSION_GUID", AuthorizationWeb.sessionGuidForApi)
                .when()
                .get("/V1/wishlist/items")
                .then()
                .spec(responseSpecWithStatusCode200)
                .extract().as(FavouritesModel.class);

        assertThat(response.getItems().length).isEqualTo(0);
    }

    @Step("Проверить, что при добавлении товара '{productId}' в избранное, получаем ошибку 'Доступ запрещён.', " +
            "когда в запросе отсутствует RIGLA_WEB_SESSION_GUID")
    public void checkAddProductToFavouritesNoSessionGuid(String productId) {
        ItemModel productItem = new ItemModel();
        productItem.setSku(productId);

        FavouritesModel productData = new FavouritesModel();
        productData.setItems(new ItemModel[]{productItem});

        AccessDeniedModel response = given(requestSpec)
                .body(productData)
                .when()
                .post("/V1/wishlist/items")
                .then()
                .spec(responseSpecWithStatusCode403)
                .extract().as(AccessDeniedModel.class);

        assertThat(response.getMessage()).isEqualTo("Доступ запрещён.");
    }

    @Step("Удалить товар '{productId}' из избранного")
    public void removeFromFavourites(String productId) {
        given(requestSpec)
                .cookies("RIGLA_WEB_SESSION_GUID", AuthorizationWeb.sessionGuidForApi)
                .when()
                .delete("/V1/wishlist/items/" + productId)
                .then()
                .spec(responseSpecWithStatusCode200);
    }

    @Step("Проверить, что при удалении несуществующего товара '{productId}' из избранного " +
            "получаем ошибку 'Wishlist item with sku %1 not found'")
    public void checkDeleteNonexistingProductFromFavourites(String productId) {
        NotFoundModel response = given(requestSpec)
                .cookies("RIGLA_WEB_SESSION_GUID", AuthorizationWeb.sessionGuidForApi)
                .when()
                .delete("/V1/wishlist/items/" + productId)
                .then()
                .spec(responseSpecWithStatusCode404)
                .extract().as(NotFoundModel.class);

        assertThat(response.getMessage()).isEqualTo("Wishlist item with sku %1 not found");
        assertThat(response.getParameters()).extracting(String::toString).first().isEqualTo(productId);
    }

    @Step("Проверить, что при удалении товара '{productId}' из избранного, получаем ошибку 'Доступ запрещён.', " +
            "когда в запросе отсутствует RIGLA_WEB_SESSION_GUID")
    public void checkDeleteProductFromFavouritesNoSessionGuid(String productId) {
        AccessDeniedModel response = given(requestSpec)
                .when()
                .delete("/V1/wishlist/items/" + productId)
                .then()
                .spec(responseSpecWithStatusCode403)
                .extract().as(AccessDeniedModel.class);

        assertThat(response.getMessage()).isEqualTo("Доступ запрещён.");
    }

    @Step("Проверить, что несколько товаров '{productId}' и '{product2Id}' присутствуют в избранном")
    public void checkProductsAreInFavourites(String productId, String product2Id) {
        FavouritesModel response = given(requestSpec)
                .cookies("RIGLA_WEB_SESSION_GUID", AuthorizationWeb.sessionGuidForApi)
                .when()
                .get("/V1/wishlist/items")
                .then()
                .spec(responseSpecWithStatusCode200)
                .extract().as(FavouritesModel.class);

        assertThat(response.getItems().length).isEqualTo(2);
        assertThat(response.getItems()).extracting(ItemModel::getSku).first().isEqualTo(productId);
        assertThat(response.getItems()).extracting(ItemModel::getQty).first().isEqualTo("1");
        assertThat(response.getItems()).extracting(ItemModel::getSku).element(1).isEqualTo(product2Id);
        assertThat(response.getItems()).extracting(ItemModel::getQty).element(1).isEqualTo("1");
    }

    @Step("Проверить, что при получении списка избранного, получаем ошибку 'Доступ запрещён.', " +
            "когда в запросе отсутствует RIGLA_WEB_SESSION_GUID")
    public void checkFavouritesListNoSessionGuid() {
        AccessDeniedModel response = given(requestSpec)
                .when()
                .get("/V1/wishlist/items")
                .then()
                .spec(responseSpecWithStatusCode403)
                .extract().as(AccessDeniedModel.class);

        assertThat(response.getMessage()).isEqualTo("Доступ запрещён.");
    }
}
