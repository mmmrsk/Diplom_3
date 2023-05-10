package ru.yandex.practicum.stellarburgers.clients;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.yandex.practicum.stellarburgers.models.UserCreds;
import ru.yandex.practicum.stellarburgers.models.UserModel;

public class UserClient extends Client {
    private final String REGISTER = "/auth/register";
    private final String LOGIN = "/auth/login";
    private final String DELETE = "/auth/user?={user}";
    @Step("New User Registration")
    public Response registerNewUser(UserModel user) {
        return reqSpec
                .body(user)
                .when()
                .post(REGISTER);
    }

    @Step("User login")
    public Response login(UserCreds creds) {
        return reqSpec
                .body(creds)
                .when()
                .post(LOGIN);
    }

    @Step("Delete User")
    public void delete(String user, String bearerToken) {

        reqSpec
                .header("authorization", bearerToken)
                .pathParams("user", user)
                .when()
                .delete(DELETE)
                .then().log().all()
                .assertThat()
                .statusCode(202);
    }
}
