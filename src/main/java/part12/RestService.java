package part12;

import part12.users.UsersService;
import part12.posts.PostsService;

public class RestService {

    public static UsersService getUsersService() {
        return new UsersService();
    }

    public static PostsService getPostsService() {
        return new PostsService();
    }
}
