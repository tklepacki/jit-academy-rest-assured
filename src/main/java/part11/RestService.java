package part11;

import part11.users.UsersService;

public class RestService {
    public static UsersService getUsersService() {
        return new UsersService();
    }
}

