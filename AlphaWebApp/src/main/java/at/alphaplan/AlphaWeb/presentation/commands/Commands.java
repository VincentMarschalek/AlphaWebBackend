package at.alphaplan.AlphaWeb.presentation.commands;

import java.util.List;

public abstract class Commands {

  // --------Registration
  public record UserRegistrationCommand(
      String email, String password, String lastName, String firstName) {
    public String email() {
      return email.trim().toLowerCase();
    }
  }

  // ---------Verification

  public record UserVerificationCommand(String userId, String tokenId) {}

  // -----------Media
  public record MediaMetaCommand(
      String filename, String mimeType, long size, int width, int height) {}

  // -----------Product
  public record ProductCommand(
      String name, int price, String description, List<String> productPicIds) {

    public String name() {
      return name.trim();
    }

    public int price() {
      return Math.max(price, 0); // Ensuring non-negative price
    }

    public String description() {
      return description != null ? description.trim() : "";
    }

    public List<String> productPicIds() {
      return productPicIds != null ? List.copyOf(productPicIds) : List.of();
    }
  }

  // ---------------Order

  public record OrderCommand(
      String productId, String message, String address, String billingAddress) {}
}
