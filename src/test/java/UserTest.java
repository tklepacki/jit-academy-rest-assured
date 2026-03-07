import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import  static org.hamcrest.Matchers.equalTo;

public class UserTest {

    @Test
    public void getUserTest() {
        given().
                header("x-api-key", "reqres_eb68f411937a40c68226fb013febfe14").
        when().
                get("https://reqres.in/api/users/2").
                then().
                statusCode(200).
                log().all();
    }

    @Test
    public void getUserListTest() {
        given().
                header("x-api-key", "reqres_eb68f411937a40c68226fb013febfe14").
        when().
                get("https://reqres.in/api/users?page=2").
                then().
                statusCode(200).
                log().all();
    }

    @Test
    public void getUserNotFoundTest() {
        given().
                header("x-api-key", "reqres_eb68f411937a40c68226fb013febfe14").
        when().
                get("https://reqres.in/api/users/999").
                then().
                statusCode(404).
                log().all();
    }

    @Test
    public void getUserUnauthorizedTest() {
        given().
                header("x-api-key", "invalid_token").
        when().
                get("https://reqres.in/api/users/2").
                then().
                statusCode(403).
                body("error", equalTo("invalid_api_key")).
                log().all();
    }
}