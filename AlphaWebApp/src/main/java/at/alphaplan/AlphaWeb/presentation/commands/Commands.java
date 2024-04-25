package at.alphaplan.AlphaWeb.presentation.commands;

public class Commands
{
    public record UserRegistrationCommand(String email, String password){};
    public record UserRegistrationCommand_EmailForm(String email)
    {
    }
}
