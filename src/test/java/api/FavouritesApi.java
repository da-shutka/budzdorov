package api;

import auth.AuthorizationWeb;
import io.qameta.allure.Step;
import models.GetFavouritesListModel;
import models.ItemModel;

import static io.restassured.RestAssured.given;
import static specs.RiglaSpec.requestSpec;
import static specs.RiglaSpec.responseSpecWithStatusCode200;

import static org.assertj.core.api.Assertions.assertThat;

public class FavouritesApi {

    /*@Step("Проверка наличия добавленного товара в избранном")
    public void checkAddedProductIsInFavourites(String productId) {
        GetFavouritesListModel response = given(requestSpec)
                .cookies("RIGLA_WEB_SESSION_GUID", AuthorizationWeb.sessionGuidForApi)
                .when()
                .get("/V1/wishlist/items")
                .then()
                .spec(responseSpecWithStatusCode200)
                .extract().as(GetFavouritesListModel.class);

        assertThat(response.getItems()).extracting(ItemModel::getSku).anyMatch(sku -> sku.matches(productId));
    }*/

    @Step("API: Удалить товар из избранного")
    public void removeFromFavourites(String productId) {
        given(requestSpec)
                .cookies("RIGLA_WEB_SESSION_GUID", AuthorizationWeb.sessionGuidForApi)
                .when()
                .delete("/V1/wishlist/items/" + productId)
                .then()
                .spec(responseSpecWithStatusCode200);
    }

    @Step("API: Проверить, что товар удален из Избранного")
    public void checkNoRemovedProductIsInFavourites(String productId) {
        GetFavouritesListModel response = given(requestSpec)
                .cookies("RIGLA_WEB_SESSION_GUID", AuthorizationWeb.sessionGuidForApi)
                .when()
                .get("/V1/wishlist/items")
                .then()
                .spec(responseSpecWithStatusCode200)
                .extract().as(GetFavouritesListModel.class);

        assertThat(response.getItems()).extracting(ItemModel::getSku).doesNotContain(productId);
    }
}
