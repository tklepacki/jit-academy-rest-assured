package part13;

import org.junit.jupiter.api.*;
import part13.factories.PostTestDataFactory;
import part13.posts.Post;

import static io.restassured.RestAssured.withArgs;
import static org.hamcrest.Matchers.*;

public class PostTest {

    private final Post addPostBody = PostTestDataFactory.validPost();
    private final Post editPostBody = PostTestDataFactory.updatedPost();

    @Test
    public void addPostTest() {
        String addedPostId = RestService.getPostsService().addPost(addPostBody).
                then().
                body("views", equalTo(addPostBody.getViews())).
                body("title", equalTo(addPostBody.getTitle())).
                statusCode(201).
                extract().
                path("id");

        RestService.getPostsService().getPost(addedPostId).
                then().
                body("id", equalTo(addedPostId)).
                body("views", equalTo(addPostBody.getViews())).
                body("title", equalTo(addPostBody.getTitle())).
                statusCode(200);
    }

    @Test
    public void editPostTest() {

        String addedPostId = RestService.getPostsService().addPost(addPostBody).
                then().
                statusCode(201).
                extract().
                path("id");

        String updatedPostId = RestService.getPostsService().editPost(addedPostId, editPostBody).
                then().
                body("id", equalTo(addedPostId)).
                body("views", equalTo(editPostBody.getViews())).
                body("title", equalTo(editPostBody.getTitle())).
                statusCode(200).
                extract().
                path("id");

        RestService.getPostsService().getPost(updatedPostId).
                then().
                body("id", equalTo(addedPostId)).
                body("views", equalTo(editPostBody.getViews())).
                body("title", equalTo(editPostBody.getTitle())).
                statusCode(200);
    }

    @Test
    public void getPostTest() {
        String addedPostId = RestService.getPostsService().addPost(addPostBody).
                then().
                statusCode(201).
                extract().
                path("id");

        RestService.getPostsService().getPostList().
                then().
                body("find { it.id == '%s' }.id", withArgs(addedPostId), equalTo(addedPostId)).
                body("find { it.id == '%s' }.title", withArgs(addedPostId), equalTo(addPostBody.getTitle())).
                body("find { it.id == '%s' }.views", withArgs(addedPostId), equalTo(addPostBody.getViews())).
                statusCode(200);
    }

    @Test
    public void deletePostTest() {

        String addedPostId = RestService.getPostsService().addPost(addPostBody).
                then().
                statusCode(201).
                extract().
                path("id");

        RestService.getPostsService().deletePost(addedPostId).
                then().
                statusCode(200);

        RestService.getPostsService().getPostList().
                then().
                body("id", not(hasItems(addedPostId)));
    }
}
