import posts.PostService;
import users.UserService;

public class RestService {

    public static UserService getUserService() {
        return new UserService();
    }

    public static PostService getPostService() {
        return new PostService();
    }
}
