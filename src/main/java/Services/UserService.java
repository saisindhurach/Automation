package services;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import utilities.Config;

public class UserService {
    private String baseUrl = Config.getBaseUrl();
    private String authToken;

    public UserService(String token) {
        this.authToken = token;
    }

    public Response getUser(int userId) {
        return RestAssured
                .given()
                .header("Authorization", "Bearer " + authToken)
                .when()
                .get(baseUrl + "/users/" + userId);
    }

    public Response createUser(String payload) {
        return RestAssured
                .given()
                .header("Authorization", "Bearer " + authToken)
                .log().all()
                .contentType("application/json")
                .body(payload)
                .when()
                .post(baseUrl + "/users");
    }
}
