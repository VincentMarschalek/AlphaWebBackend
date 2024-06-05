package at.alphaplan.AlphaWeb.presentation;

import static at.alphaplan.AlphaWeb.fixture.UserCommandFixture.USER_REGISTRATION_COMMAND;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;

public class UserRegistrationControllerTest extends AbstractControllerTest {
  // GIVEN/WHEN/THEN -- Behaviour Driven Testing
  @Test
  public void givenNonExistingUser_whenUserRegisters_ThenAccountIsCreated() {

    var response = ApiHelpers.registerUser(USER_REGISTRATION_COMMAND);

    // Then
    assertThat(response.getStatusCode(), equalTo(201));
  }
}
