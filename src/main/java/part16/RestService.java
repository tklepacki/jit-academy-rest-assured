package part16;

import part16.posts.PostsService;
import part16.users.UsersService;

public class RestService {

    public static UsersService getUsersService() {
        return new UsersService();
    }

    public static PostsService getPostsService() {
        return new PostsService();
    }
}
