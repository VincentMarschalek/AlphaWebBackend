package at.alphaplan.AlphaWeb.presentation.commands;

public class Commands {

  // --------Registration
  public record UserRegistrationCommand(
      String email, String password, String lastName, String firstName) {
    public String email() {
      return email.trim().toLowerCase();
    }
  }

  // ---------Verification

  public record UserVerificationCommand(String userId, String tokenId) {}
}
