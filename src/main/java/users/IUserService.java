package users;

import io.restassured.response.Response;

public interface IUserService {
    Response getUserList(Integer pageId);
}
