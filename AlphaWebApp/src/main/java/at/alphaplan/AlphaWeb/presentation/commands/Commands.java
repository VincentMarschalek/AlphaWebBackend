package at.alphaplan.AlphaWeb.presentation.commands;

public class Commands {



  //--------Registration
  public record UserRegistrationCommand(String email, String password) {
    public String email() {
      return email.trim().toLowerCase();
    }
  }



  //---------Verification

  public record UserVerificationCommand(String userId, String tokenId) {}


}
