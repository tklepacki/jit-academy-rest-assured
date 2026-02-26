package part14;

import org.junit.jupiter.api.*;
import part14.posts.Post;

import static io.restassured.RestAssured.withArgs;
import static org.hamcrest.Matchers.*;

public class PostTest {

    private String createdPostId;

    @AfterEach
    void cleanup() {
        if (createdPostId != null) {
            RestService.getPostsService().deletePost(createdPostId);
        }
    }

    @Test
    public void addPostTest() {

        Post post = new Post.Builder()
                .title("TestTitle")
                .views(200)
                .build();

        createdPostId = RestService.getPostsService().addPost(post).
                then().
                body("views", equalTo(post.getViews())).
                body("title", equalTo(post.getTitle())).
                statusCode(201).
                extract().
                path("id");

        RestService.getPostsService().getPost(createdPostId).
                then().
                body("id", equalTo(createdPostId)).
                body("views", equalTo(post.getViews())).
                body("title", equalTo(post.getTitle())).
                statusCode(200);
    }

    @Test
    public void editPostTest() {

        Post post = new Post.Builder()
                .title("TestTitle")
                .views(200)
                .build();

        createdPostId = RestService.getPostsService().addPost(post).
                then().
                statusCode(201).
                extract().
                path("id");

        Post updatedPost = new Post.Builder()
                .title("TestTitleUpdated")
                .views(300)
                .build();

        RestService.getPostsService().editPost(createdPostId, updatedPost).
                then().
                body("id", equalTo(createdPostId)).
                body("views", equalTo(updatedPost.getViews())).
                body("title", equalTo(updatedPost.getTitle())).
                statusCode(200);

        RestService.getPostsService().getPost(createdPostId).
                then().
                body("id", equalTo(createdPostId)).
                body("views", equalTo(updatedPost.getViews())).
                body("title", equalTo(updatedPost.getTitle())).
                statusCode(200);
    }

    @Test
    public void getPostListTest() {

        Post post = new Post.Builder()
                .title("TestTitle")
                .views(200)
                .build();

        createdPostId = RestService.getPostsService().addPost(post).
                then().
                statusCode(201).
                extract().
                path("id");

        RestService.getPostsService().getPostList().
                then().
                body("find { it.id == '%s' }.id", withArgs(createdPostId), equalTo(createdPostId)).
                body("find { it.id == '%s' }.title", withArgs(createdPostId), equalTo(post.getTitle())).
                body("find { it.id == '%s' }.views", withArgs(createdPostId), equalTo(post.getViews())).
                statusCode(200);
    }

    @Test
    public void deletePostTest() {

        Post post = new Post.Builder()
                .title("TestTitle")
                .views(200)
                .build();

        createdPostId = RestService.getPostsService().addPost(post).
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
