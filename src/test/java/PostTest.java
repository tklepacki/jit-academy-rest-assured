import org.junit.jupiter.api.*;
import posts.Post;

import static io.restassured.RestAssured.withArgs;
import static org.hamcrest.Matchers.*;

public class PostTest {

    private final Post addPostBody = FileHelper.generateObjectFromResource("addPostBody.json", Post.class);
    private final Post editPostBody = FileHelper.generateObjectFromResource("editPostBody.json", Post.class);

    private String createdPostId;

    @AfterEach
    void cleanup() {
        if (createdPostId != null) {
            RestService.getPostsService().deletePost(createdPostId);
        }
    }

    @Test
    public void addPostTest() {
        createdPostId = RestService.getPostsService().addPost(addPostBody).
                then().
                body("views", equalTo(addPostBody.getViews())).
                body("title", equalTo(addPostBody.getTitle())).
                statusCode(201).
                extract().
                path("id");

        RestService.getPostsService().getPost(createdPostId).
                then().
                body("id", equalTo(createdPostId)).
                body("views", equalTo(addPostBody.getViews())).
                body("title", equalTo(addPostBody.getTitle())).
                statusCode(200);
    }

    @Test
    public void editPostTest() {

        createdPostId = RestService.getPostsService().addPost(addPostBody).
                then().
                statusCode(201).
                extract().
                path("id");

        RestService.getPostsService().editPost(createdPostId, editPostBody).
                then().
                body("id", equalTo(createdPostId)).
                body("views", equalTo(editPostBody.getViews())).
                body("title", equalTo(editPostBody.getTitle())).
                statusCode(200);

        RestService.getPostsService().getPost(createdPostId).
                then().
                body("id", equalTo(createdPostId)).
                body("views", equalTo(editPostBody.getViews())).
                body("title", equalTo(editPostBody.getTitle())).
                statusCode(200);
    }

    @Test
    public void getPostTest() {
        createdPostId = RestService.getPostsService().addPost(addPostBody).
                then().
                statusCode(201).
                extract().
                path("id");

        RestService.getPostsService().getPostList().
                then().
                body("find { it.id == '%s' }.id", withArgs(createdPostId), equalTo(createdPostId)).
                body("find { it.id == '%s' }.title", withArgs(createdPostId), equalTo(addPostBody.getTitle())).
                body("find { it.id == '%s' }.views", withArgs(createdPostId), equalTo(addPostBody.getViews())).
                statusCode(200);
    }

    @Test
    public void deletePostTest() {

        createdPostId = RestService.getPostsService().addPost(addPostBody).
                then().
                statusCode(201).
                extract().
                path("id");

        RestService.getPostsService().deletePost(createdPostId).
                then().
                statusCode(200);

        RestService.getPostsService().getPostList().
                then().
                body("id", not(hasItems(createdPostId)));

        createdPostId = null;
    }
}