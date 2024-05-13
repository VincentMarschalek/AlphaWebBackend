package at.alphaplan.AlphaWeb.presentation;

import static at.alphaplan.AlphaWeb.presentation.commands.Commands.*;
import static io.restassured.RestAssured.given;

import io.restassured.response.Response;

public class ApiHelpers {
  public static final String API_REGISTRATION = "/api/registration";

  public static Response registerUser(UserRegistrationCommand command) {
    // spotless:off
    Response response =
        given()
            .contentType("application/json")
            .body(command)
            .when()
            .post(API_REGISTRATION)
            .then()
            .extract()
            .response();
    // spotless:on

    return response;
  }
}
