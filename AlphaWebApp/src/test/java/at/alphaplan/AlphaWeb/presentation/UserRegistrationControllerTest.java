package at.alphaplan.AlphaWeb.presentation;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static at.alphaplan.AlphaWeb.fixture.UserCommandFixture.USER_REGISTRATION_COMMAND;
import static at.alphaplan.AlphaWeb.presentation.commands.Commands.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class UserRegistrationControllerTest extends AbstractControllerTest
{
    // GIVEN/WHEN/THEN -- Behaviour Driven Testing
    @Test
    public void whenUserRegisters_ThenAccountIsCreated()
    {
        var response = getResponse();

        //Then
        assertThat(response.getStatusCode(), equalTo(201));
    }

    private static Response getResponse() {
        return ApiHelpers.registerUser(USER_REGISTRATION_COMMAND);
    }
}
