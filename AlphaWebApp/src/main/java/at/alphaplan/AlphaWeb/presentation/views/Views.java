package at.alphaplan.AlphaWeb.presentation.views;

import at.alphaplan.AlphaWeb.domain.user.Role;

public abstract class Views {

  public record UserView(String email, Role role, String firstName, String lastName) {}
}
