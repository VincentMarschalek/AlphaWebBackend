package at.alphaplan.AlphaWeb.fixture;

import at.alphaplan.AlphaWeb.presentation.commands.Commands;

import static at.alphaplan.AlphaWeb.fixture.UserFixture.*;
import static at.alphaplan.AlphaWeb.presentation.commands.Commands.*;
public class UserCommandFixture
{
    public static final UserRegistrationCommand USER_REGISTRATION_COMMAND =
            new UserRegistrationCommand(EMAIL, PASSWORD);
}
