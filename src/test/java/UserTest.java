import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class UserTest {

    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec;

    @BeforeAll
    public static void createSpecification() {
        requestSpec = new RequestSpecBuilder().
            setBaseUri("https://reqres.in/api/users").
            addHeader("x-api-key", "reqres_eb68f411937a40c68226fb013febfe14").
            build();

        responseSpec = new ResponseSpecBuilder().
            expectStatusCode(200).
            expectContentType("application/json;charset=UTF-8").
            build();
    }   

    @Test
    public void getUserTest() {
        given().
                header("x-api-key", "reqres_eb68f411937a40c68226fb013febfe14").
        when().
                get("https://reqres.in/api/users/2").
                then().
                body(matchesJsonSchemaInClasspath("schemas/user.json")).
                statusCode(200).
                time(lessThan(2L), TimeUnit.SECONDS).
                log().all();
    }

    @Test
    public void getUserListTest() {
        given().
            spec(requestSpec).
            queryParam("page", "2").
        when().
        get().
        then().
                spec(responseSpec).
                body(matchesJsonSchemaInClasspath("schemas/userList.json")).
                time(lessThan(2L), TimeUnit.SECONDS).
                log().all();
    }

    @Test
    public void getUserNotFoundTest() {
        given().
                header("x-api-key", "reqres_eb68f411937a40c68226fb013febfe14").
        when().
                get("https://reqres.in/api/users/999").
        then().
                statusCode(404);
    }

    @Test
    public void getUserUnauthorizedTest() {
        given().
                header("x-api-key", "invalid_token").
        when().
                get("https://reqres.in/api/users/2").
        then().
                statusCode(403).
                body("error", equalTo("invalid_api_key"));
    }
}