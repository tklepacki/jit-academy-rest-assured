package users;

import io.restassured.response.Response;

public interface IUsersService {

    Response getUserList(Integer pageId);

}