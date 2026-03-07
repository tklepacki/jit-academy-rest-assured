package common;

import org.junit.jupiter.api.BeforeAll;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public abstract class BaseTest {
    protected static RequestSpecification requestSpec;
    protected static ResponseSpecification responseSpec;

    private static final String BASE_URI = "https://reqres.in/api/users";
    private static final String API_KEY = "reqres_eb68f411937a40c68226fb013febfe14";    

    @BeforeAll
    public static void createSpecifications() {
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
