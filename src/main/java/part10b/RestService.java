package part10b;

import part10b.users.UsersService;
import part10b.posts.PostsService;

public class RestService {

    public static UsersService getUsersService() {
        return new UsersService();
    }

    public static PostsService getPostsService() {
        return new PostsService();
    }
}
