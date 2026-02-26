package part13.factories;

import part13.posts.Post;

public class PostTestDataFactory {

    public static Post validPost() {
        return new Post.Builder()
                .title("TestTitle")
                .views(200)
                .build();
    }

    public static Post updatedPost() {
        return new Post.Builder()
                .title("TestTitleUpdated")
                .views(300)
                .build();
    }
}
