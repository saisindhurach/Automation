package services;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import utilities.Config;

public class AuthService {
    public String getAuthToken() {
        String payload = String.format("{\"email\": \"%s\", \"password\": \"%s\"}", 
                                        Config.getUsername(), Config.getPassword());

        Response response = RestAssured
                .given()
                .contentType("application/json")
                .body(payload)
                .log().all()
                .when()
                .post(Config.getAuthTokenUrl());

        return response.jsonPath().getString("token");
    }
}
