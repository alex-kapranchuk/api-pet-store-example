package client;

import io.restassured.response.Response;
import model.UserModel;

import static io.restassured.RestAssured.given;

public class UserClient extends HttpClient {

    public UserClient() {
        super("user");
    }

    public Response createUser(UserModel userModel) {
        return given(defaultRequestSpecification)
                .body(userModel)
                .post();
    }

    public Response getUser(String username) {
        return given(defaultRequestSpecification)
                .get("/{username}", username);
    }

    public Response updateUser(String username, UserModel userModel) {
        return given(defaultRequestSpecification)
                .body(userModel)
                .put("/{username}", username);
    }

    public Response deleteUser(String username) {
        return given(defaultRequestSpecification)
                .delete("/{username}", username);
    }
    public Response loginUser(String username, String password){
        return given(defaultRequestSpecification)
                .pathParam("username", username)
                .pathParam("password", password)
                .get("/login?username={username}&password={password}");
    }

    public Response LogoutUser(){
        return given(defaultRequestSpecification)
                .get("/logout");
    }
}
