package part10;

import org.junit.jupiter.api.*;
import part10.posts.Post;

import static io.restassured.RestAssured.withArgs;
import static org.hamcrest.Matchers.*;

public class PostTest {

    @Test
    public void addPostTest() {

        Post post = new Post();
        post.setTitle("TestTitle");
        post.setViews(200);

        String addedPostId = RestService.getPostsService().addPost(post).
                then().
                body("views", equalTo(post.getViews())).
                body("title", equalTo(post.getTitle())).
                statusCode(201).
                extract().
                path("id");

        RestService.getPostsService().getPost(addedPostId).
                then().
                body("id", equalTo(addedPostId)).
                body("views", equalTo(post.getViews())).
                body("title", equalTo(post.getTitle())).
                statusCode(200);
    }

    @Test
    public void editPostTest() {

        Post post = new Post();
        post.setTitle("TestTitle");
        post.setViews(200);

        String addedPostId = RestService.getPostsService().addPost(post).
                then().
                statusCode(201).
                extract().
                path("id");

        Post updatedPost = new Post();
        updatedPost.setTitle("TestTitleUpdated");
        updatedPost.setViews(300);

        RestService.getPostsService().editPost(addedPostId, updatedPost).
                then().
                body("id", equalTo(addedPostId)).
                body("views", equalTo(updatedPost.getViews())).
                body("title", equalTo(updatedPost.getTitle())).
                statusCode(200);

        RestService.getPostsService().getPost(addedPostId).
                then().
                body("id", equalTo(addedPostId)).
                body("views", equalTo(updatedPost.getViews())).
                body("title", equalTo(updatedPost.getTitle())).
                statusCode(200);
    }

    @Test
    public void getPostListTest() {

        Post post = new Post();
        post.setTitle("TestTitle");
        post.setViews(200);

        String addedPostId = RestService.getPostsService().addPost(post).
                then().
                statusCode(201).
                extract().
                path("id");

        RestService.getPostsService().getPostList().
                then().
                body("find { it.id == '%s' }.id", withArgs(addedPostId), equalTo(addedPostId)).
                body("find { it.id == '%s' }.title", withArgs(addedPostId), equalTo(post.getTitle())).
                body("find { it.id == '%s' }.views", withArgs(addedPostId), equalTo(post.getViews())).
                statusCode(200);
    }

    @Test
    public void deletePostTest() {

        Post post = new Post();
        post.setTitle("TestTitle");
        post.setViews(200);

        String addedPostId = RestService.getPostsService().addPost(post).
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
