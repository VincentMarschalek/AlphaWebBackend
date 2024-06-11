package at.alphaplan.AlphaWeb.presentation.views;

import at.alphaplan.AlphaWeb.domain.order.Order;
import at.alphaplan.AlphaWeb.domain.user.Role;
import at.alphaplan.AlphaWeb.domain.user.User;
import java.util.List;

public abstract class Views {

  public record UserView(String email, List<Role> role, String firstName, String lastName) {
    public UserView(User user) {
      this(
          user.getEmail(),
          user.getRole(),
          user.getProfile().getFirstName(),
          user.getProfile().getLastName());
    }
  }

  public record OrderView(Order order) {}

  public record LoginView(UserView userView, List<Order> orders) {}
}
