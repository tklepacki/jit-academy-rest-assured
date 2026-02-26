package part06;

import part06.common.BaseTest;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

import org.junit.jupiter.api.Test;

public class UserTest extends BaseTest {

    @Test
    public void getUserTest() {
        given().
                spec(requestSpec).
                pathParam("userId", 2).

                when().
                get("/{userId}").

                then().
                body(matchesJsonSchemaInClasspath("schemas/user.json")).
                body("data.id", equalTo(2)).
                body("data.email", equalTo("janet.weaver@reqres.in")).
                body("data.first_name", equalTo("Janet")).
                body("data.last_name", equalTo("Weaver")).
                body("data.avatar", equalTo("https://reqres.in/img/faces/2-image.jpg")).
                spec(responseSpec);
    }

    @Test
    public void getUserListTest() {
        given().
                spec(requestSpec).
                queryParam("page", "2").

                when().
                get().

                then().
                body(matchesJsonSchemaInClasspath("schemas/userList.json")).
                body("page", equalTo(2)).
                body("per_page", equalTo(6)).
                body("total", equalTo(12)).
                body("total_pages", equalTo(2)).

                body("data.id", hasItems(7, 8, 9, 10, 11, 12)).
                spec(responseSpec);
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
