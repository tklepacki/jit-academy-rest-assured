import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class UserTest {

    static Stream<Arguments> userListData() {
        return Stream.of(
                arguments(1, 6, 12, 2, 1, "george.bluth@reqres.in", "George", "Bluth", "https://reqres.in/img/faces/1-image.jpg"),
                arguments(2, 6, 12, 2, 7, "michael.lawson@reqres.in", "Michael", "Lawson", "https://reqres.in/img/faces/7-image.jpg")
        );
    }

/*    @Test
    public void getUserTest() {
        given().
                spec(requestSpec).
                pathParam("userId", 2).

                when().
                get("/{userId}").

                then().
                // body(matchesJsonSchemaInClasspath("schemas/user.json")).
                rootPath("data").
                body("id", equalTo(2)).
                body("email", equalTo("janet.weaver@reqres.in")).
                body("first_name", equalTo("Janet")).
                body("last_name", equalTo("Weaver")).
                body("avatar", equalTo("https://reqres.in/img/faces/2-image.jpg")).
                contentType("application/json;charset=UTF-8").
                statusCode(200);
    }*/

    @ParameterizedTest
    @MethodSource("userListData")
    public void getUserListTest(Integer pageId, Integer perPage, Integer total, Integer totalPages, Integer userId, String email, String firstName, String lastName, String avatar) {
                RestService.getUserService().getUserList(pageId).
                then().
                body(matchesJsonSchemaInClasspath("schemas/userList.json")).
                body("page", equalTo(pageId)).
                body("per_page", equalTo(perPage)).
                body("total", equalTo(total)).
                body("total_pages", equalTo(totalPages)).

/*                body("data.find { it.id > 8 }.id", equalTo(9)).
                body("data.findAll { it.id > 8 }.id", hasItems(9, 10, 11, 12)).
                body("data.findAll { it.id == 10 }.last_name", hasItems("Fields")).
                body("data.findAll { it.id > 8 }.first_name", hasItems("Tobias", "Byron", "George", "Rachel")).
                body("data.max { it.id }.id", equalTo(12)).
                body("data.min { it.id }.first_name", equalTo("Michael")).
                body("data.collect { it.id }.sum()", equalTo(57)).
                body("data.findAll { it.id > 8 }.find {it.first_name == 'Tobias'}.avatar", equalTo("https://reqres.in/img/faces/9-image.jpg")).*/
        rootPath("data").
                body("id[0]", equalTo(userId)).
                body("email[0]", equalTo(email)).
                body("first_name[0]", equalTo(firstName)).
                body("last_name[0]", equalTo(lastName)).
                body("avatar[0]", equalTo(avatar)).

/*
                body("id", hasItems(7, 8, 9, 10, 11, 12)).
                body("email", hasItems("michael.lawson@reqres.in", "lindsay.ferguson@reqres.in", "tobias.funke@reqres.in", "byron.fields@reqres.in", "george.edwards@reqres.in", "rachel.howell@reqres.in")).
                body("first_name", hasItems("Michael", "Lindsay", "Tobias", "Byron", "George", "Rachel")).
                body("last_name", hasItems("Lawson", "Ferguson", "Funke", "Fields", "Edwards", "Howell")).
*/

        contentType("application/json;charset=UTF-8").
                statusCode(200).
                log().all();
    }

    @Test
    public void getStarWarsPeopleList() {
        when().
                get("https://swapi.py4e.com/api/people").

                then().
                body("results.findAll { it.height > '180' }.name", hasItems("R2-D2", "Darth Vader", "R5-D4", "Biggs Darklighter", "Obi-Wan Kenobi")).
                body("results.findAll { it.gender == 'female' }.size()", equalTo(2)).
                statusCode(200).
                log().all();
    }
}