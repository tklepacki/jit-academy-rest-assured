import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

public class UserTest {

    @Test
    public void getUserTest() {
        given().
            header("x-api-key", "reqres_2f08e5df916845e1b46a779684000666").
        when().
            get("https://reqres.in/api/users/2").
        then().
            statusCode(200).
            log().all();
    }
}
