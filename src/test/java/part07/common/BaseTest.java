package part07.common;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseTest {

    protected static RequestSpecification requestSpec;

    private static final String BASE_URI = "https://reqres.in/api/users";
    private static final String API_KEY = "reqres_eb68f411937a40c68226fb013febfe14";

    @BeforeAll
    public static void setup() {
        requestSpec = new RequestSpecBuilder().
                setBaseUri(BASE_URI).
                addHeader("x-api-key", API_KEY).
                build();
    }
}
