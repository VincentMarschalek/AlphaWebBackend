package at.alphaplan.AlphaWeb.presentation.commands;

public class Commands {
  public record UserRegistrationCommand(String email, String password)
  {
    public String email() {
      return email.trim().toLowerCase();
    }

  }

  public record  UserVerificationCommand(String userId, String tokenId) {}
}
