package at.alphaplan.AlphaWeb.fixture;

import static at.alphaplan.AlphaWeb.security.PasswordService.*;
import static org.springframework.security.crypto.factory.PasswordEncoderFactories.createDelegatingPasswordEncoder;

import at.alphaplan.AlphaWeb.domain.user.Role;
import at.alphaplan.AlphaWeb.domain.user.User;
import at.alphaplan.AlphaWeb.security.PasswordService;

public class UserFixture {
  public static final String EMAIL = "vincent.marschalek@yahoo.de";
  public static final String PASSWORD = "AlleMeineEntchenSchwimmen";

  private static final PasswordService passwordService =
      new PasswordService(createDelegatingPasswordEncoder());

  private static final EncodedPassword encodedPassword = passwordService.encode(PASSWORD);

  public static User createUser() {
    var user = new User(EMAIL, Role.USER, encodedPassword);
    return user;
  }
}
