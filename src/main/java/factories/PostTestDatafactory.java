package factories;

import posts.Post;

public class PostTestDatafactory {

    public static Post createPost() {
        return new Post.Builder()
                .title("TestTitle")
                .views(100).build();
    }

        public static Post updatedPost() {
        return new Post.Builder()
                .title("TestTitleUpdated")
                .views(300).build();
    }
}
