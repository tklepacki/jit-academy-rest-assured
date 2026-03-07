import users.UsersService;

public class RestService {
    public static UsersService getUsersService() {
        return new UsersService();
    }
}

