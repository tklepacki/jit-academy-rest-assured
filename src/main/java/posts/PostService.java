package posts;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class PostService implements IPostService {

    private static final String USERS_ENDPOINT = "http://localhost:3000/posts";

    RequestSpecification requestSpec = new RequestSpecBuilder().
            setBaseUri(USERS_ENDPOINT).
            setContentType(ContentType.JSON).
            build();

    public Response getPost(String postId) {
        return given().
                spec(requestSpec).pathParam("postId", postId).
                when().
                get("{postId}");
    }

    public Response getPostList() {
        return given().
                spec(requestSpec).
                when().
                get();
    }

    public Response addPost(Post post) {
        return given().
                spec(requestSpec).
                body(post).
                when().
                post();
    }

    public Response editPost(String postId, Post post) {
        return given().
                spec(requestSpec).
                pathParam("postId", postId).
                body(post).
                when().
                put("{postId}");
    }

    public Response deletePost(String postId) {
        return given().
                spec(requestSpec).
                pathParam("postId", postId).
                when().
                delete("{postId}");
    }
}