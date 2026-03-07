import common.BaseTest;

import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

public class UserTest extends BaseTest {

    @Test
    public void getUserTest() {
        given().
                spec(requestSpec).
                pathParam("userId", 2).

                when().
                get("/{userId}").

                then().
                spec(responseSpec).
                body(matchesJsonSchemaInClasspath("schemas/user.json")).
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
                log().all();
    }

    @Test
    public void getUserNotFoundTest() {
        given().
                spec(requestSpec).
                pathParam("userId", 999).

                when().
                get("/{userId}").

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