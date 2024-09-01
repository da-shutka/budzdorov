package api.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static common.helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;

public class RiglaSpec {

    public static final RequestSpecification requestSpec = with()
            .filter(withCustomTemplates())
            .contentType(ContentType.JSON)
            .log().all();

    public static final ResponseSpecification responseSpecWithStatusCode200 = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(LogDetail.ALL)
            .build();

    public static final ResponseSpecification responseSpecWithStatusCode403 = new ResponseSpecBuilder()
            .expectStatusCode(403)
            .log(LogDetail.ALL)
            .build();

    public static final ResponseSpecification responseSpecWithStatusCode404 = new ResponseSpecBuilder()
            .expectStatusCode(404)
            .log(LogDetail.ALL)
            .build();
}
