package at.alphaplan.AlphaWeb.fixture;

import static at.alphaplan.AlphaWeb.security.password.PasswordService.EncodedPassword;
import static org.springframework.security.crypto.factory.PasswordEncoderFactories.createDelegatingPasswordEncoder;

import at.alphaplan.AlphaWeb.domain.user.Role;
import at.alphaplan.AlphaWeb.domain.user.User;
import at.alphaplan.AlphaWeb.security.password.PasswordService;

public class UserFixture {
  public static final String EMAIL = "vincent.marschalek@yahoo.de";
  public static final String PASSWORD = "AlleMeineEntchenSchwimmen";

  public static final String LAST_NAME = "Marschalek";
  public static final String FIRST_NAME = "Vincent";

  private static final PasswordService passwordService =
      new PasswordService(createDelegatingPasswordEncoder());

  private static final EncodedPassword encodedPassword = passwordService.encode(PASSWORD);

  public static User createUser() {
    var user = new User(EMAIL, Role.USER, encodedPassword);
    return user;
  }
}
