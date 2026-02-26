package part13.posts;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = Post.Builder.class)
public class Post {

    private final String title;
    private final int views;
    private final String id;

    private Post(Builder builder) {
        this.title = builder.title;
        this.views = builder.views;
        this.id = builder.id;
    }

    public String getTitle() {
        return title;
    }

    public int getViews() {
        return views;
    }

    public String getId() {
        return id;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {

        private String title;
        private int views;
        private String id;

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder views(int views) {
            this.views = views;
            return this;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Post build() {
            return new Post(this);
        }
    }
}
