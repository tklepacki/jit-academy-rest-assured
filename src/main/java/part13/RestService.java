package part13;

import part13.posts.PostsService;
import part13.users.UsersService;

public class RestService {

    public static UsersService getUsersService() {
        return new UsersService();
    }

    public static PostsService getPostsService() {
        return new PostsService();
    }
}
