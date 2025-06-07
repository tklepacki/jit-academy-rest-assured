import org.junit.jupiter.api.*;
import posts.Post;

import static org.hamcrest.Matchers.*;

public class PostTest {

    private final Post addPostBody = (Post) FileHelper.generateObjectFromResource("addPostBody.json", Post.class);
    private final Post editPostBody = (Post) FileHelper.generateObjectFromResource("editPostBody.json", Post.class);

    @Test
    public void addPostTest() {

        String addedPostId = RestService.getPostService().addPost(addPostBody).
                then().
                body("views", equalTo(addPostBody.getViews())).
                body("title", equalTo(addPostBody.getTitle())).
                statusCode(201).
                extract().
                path("id");

        RestService.getPostService().getPost(addedPostId).
                then().
                body("id", equalTo(addedPostId)).
                body("views", equalTo(addPostBody.getViews())).
                body("title", equalTo(addPostBody.getTitle())).
                statusCode(200);
    }

    @Test
    public void editPostTest() {

        String addedPostId = RestService.getPostService().addPost(addPostBody).
                then().
                statusCode(201).
                extract().
                path("id");

        String updatedPostId = RestService.getPostService().editPost(addedPostId, editPostBody).
                then().
                body("id", equalTo(addedPostId)).
                body("views", equalTo(editPostBody.getViews())).
                body("title", equalTo(editPostBody.getTitle())).
                statusCode(200).
                extract().
                path("id");

        RestService.getPostService().getPost(updatedPostId).
                then().
                body("id", equalTo(addedPostId)).
                body("views", equalTo(editPostBody.getViews())).
                body("title", equalTo(editPostBody.getTitle())).
                statusCode(200);
    }

    @Test
    public void getPostListTest() {

        String addedPostId = RestService.getPostService().addPost(addPostBody).
                then().
                statusCode(201).
                extract().
                path("id");

        RestService.getPostService().getPostList().
                then().
                body("find { it.id == '" + addedPostId + "' }.id", equalTo(addedPostId)).
                body("find { it.id == '" + addedPostId + "' }.title", equalTo(addPostBody.getTitle())).
                body("find { it.id == '" + addedPostId + "' }.views", equalTo(addPostBody.getViews())).
                statusCode(200);
    }

    @Test
    public void deletePostTest() {

        String addedPostId = RestService.getPostService().addPost(addPostBody).
                then().
                statusCode(201).
                extract().
                path("id");

        RestService.getPostService().deletePost(addedPostId).
                then().
                statusCode(200);

        RestService.getPostService().getPostList().
                then().
                body("id", not(hasItems(addedPostId)));
    }
}

