package api.steps;

import api.auth.AuthorizationApi;
import api.models.AccessDeniedModel;
import api.models.FavoritesModel;
import api.models.ItemModel;
import api.models.NotFoundModel;
import io.qameta.allure.Step;

import static api.auth.AuthorizationApi.RIGLA_WEB_SESSION_GUID;
import static api.specs.RiglaSpec.*;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestStepsApi {

    @Step("Добавить товар '{productId}' в избранное")
    public void addProductToFavorites(String productId) {
        ItemModel productItem = new ItemModel();
        productItem.setSku(productId);

        FavoritesModel productData = new FavoritesModel();
        productData.setItems(new ItemModel[]{productItem});

        given(requestSpec)
                .cookies(RIGLA_WEB_SESSION_GUID, AuthorizationApi.sessionGuidForApi)
                .body(productData)
                .when()
                .post("/V1/wishlist/items")
                .then()
                .spec(responseSpecWithStatusCode200);
    }

    @Step("Проверить, что товар '{productId}' добавлен в избранное и его количество равно 1")
    public void checkProductIsInFavorites(String productId) {
        FavoritesModel response = given(requestSpec)
                .cookies(RIGLA_WEB_SESSION_GUID, AuthorizationApi.sessionGuidForApi)
                .when()
                .get("/V1/wishlist/items")
                .then()
                .spec(responseSpecWithStatusCode200)
                .extract().as(FavoritesModel.class);

        assertThat(response.getItems().length).isEqualTo(1);
        assertThat(response.getItems()).extracting(ItemModel::getSku).first().isEqualTo(productId);
        assertThat(response.getItems()).extracting(ItemModel::getQty).first().isEqualTo("1");
    }

    @Step("Проверить, что список избранных товаров пуст")
    public void checkFavoritesListIsEmpty() {
        FavoritesModel response = given(requestSpec)
                .cookies(RIGLA_WEB_SESSION_GUID, AuthorizationApi.sessionGuidForApi)
                .when()
                .get("/V1/wishlist/items")
                .then()
                .spec(responseSpecWithStatusCode200)
                .extract().as(FavoritesModel.class);

        assertThat(response.getItems().length).isEqualTo(0);
    }

    @Step("Проверить, что при добавлении товара '{productId}' в избранное, получаем ошибку 'Доступ запрещён.', " +
            "когда в запросе отсутствует RIGLA_WEB_SESSION_GUID")
    public void checkAddProductToFavoritesNoSessionGuid(String productId) {
        ItemModel productItem = new ItemModel();
        productItem.setSku(productId);

        FavoritesModel productData = new FavoritesModel();
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
    public void removeFromFavorites(String productId) {
        given(requestSpec)
                .cookies(RIGLA_WEB_SESSION_GUID, AuthorizationApi.sessionGuidForApi)
                .when()
                .delete("/V1/wishlist/items/" + productId)
                .then()
                .spec(responseSpecWithStatusCode200);
    }

    @Step("Проверить, что при удалении несуществующего товара '{productId}' из избранного " +
            "получаем ошибку 'Wishlist item with sku %1 not found'")
    public void checkDeleteNonexistentProductFromFavorites(String productId) {
        NotFoundModel response = given(requestSpec)
                .cookies(RIGLA_WEB_SESSION_GUID, AuthorizationApi.sessionGuidForApi)
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
    public void checkDeleteProductFromFavoritesNoSessionGuid(String productId) {
        AccessDeniedModel response = given(requestSpec)
                .when()
                .delete("/V1/wishlist/items/" + productId)
                .then()
                .spec(responseSpecWithStatusCode403)
                .extract().as(AccessDeniedModel.class);

        assertThat(response.getMessage()).isEqualTo("Доступ запрещён.");
    }

    @Step("Проверить, что несколько товаров '{productId}' и '{product2Id}' присутствуют в избранном")
    public void checkProductsAreInFavorites(String productId, String product2Id) {
        FavoritesModel response = given(requestSpec)
                .cookies(RIGLA_WEB_SESSION_GUID, AuthorizationApi.sessionGuidForApi)
                .when()
                .get("/V1/wishlist/items")
                .then()
                .spec(responseSpecWithStatusCode200)
                .extract().as(FavoritesModel.class);

        assertThat(response.getItems().length).isEqualTo(2);
        assertThat(response.getItems()).extracting(ItemModel::getSku).first().isEqualTo(productId);
        assertThat(response.getItems()).extracting(ItemModel::getQty).first().isEqualTo("1");
        assertThat(response.getItems()).extracting(ItemModel::getSku).element(1).isEqualTo(product2Id);
        assertThat(response.getItems()).extracting(ItemModel::getQty).element(1).isEqualTo("1");
    }

    @Step("Проверить, что при получении списка избранного, получаем ошибку 'Доступ запрещён.', " +
            "когда в запросе отсутствует RIGLA_WEB_SESSION_GUID")
    public void checkFavoritesListNoSessionGuid() {
        AccessDeniedModel response = given(requestSpec)
                .when()
                .get("/V1/wishlist/items")
                .then()
                .spec(responseSpecWithStatusCode403)
                .extract().as(AccessDeniedModel.class);

        assertThat(response.getMessage()).isEqualTo("Доступ запрещён.");
    }
}
