package part04b.common;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseTest {

    protected static RequestSpecification requestSpec;
    protected static ResponseSpecification responseSpec;

    private static final String BASE_URI = "https://reqres.in/api/users";
    private static final String API_KEY = "reqres_eb68f411937a40c68226fb013febfe14";

    @BeforeAll
    public static void setup() {
        requestSpec = new RequestSpecBuilder().
                setBaseUri(BASE_URI).
                addHeader("x-api-key", API_KEY).
                build();

        responseSpec = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType("application/json;charset=UTF-8").
                build();
    }
}
