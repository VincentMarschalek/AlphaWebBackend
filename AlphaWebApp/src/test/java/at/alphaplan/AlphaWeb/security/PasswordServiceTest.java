package at.alphaplan.AlphaWeb.security;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import at.alphaplan.AlphaWeb.security.PasswordService.EncodedPassword;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PasswordServiceTest {
  // 1. Test Passwort Stärke
  // FAIL: -1/2
  // SUCCESS: -3/4

  public static final String weakPassword = "password123";
  public static final String strongPassword = "AlleMeineEntchenSchwimmenInDemSee";
  private PasswordService passwordService;
  private PasswordEncoder passwordEncoder;

  @BeforeAll
  public void setup() {
    passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    passwordService = new PasswordService(passwordEncoder);
  }

  @Test
  public void encode_ShouldFail_WhenProvidingWeakPasswords() {
 
    // When/Then
    assertThrows(IllegalArgumentException.class, () -> passwordService.encoded(weakPassword));
  }

  @Test
  public void encode_ShouldPass_WhenProvidingStrongPasswords() {

    // When/Then
    passwordService.encoded(strongPassword);
  }

  @Test
  public void encode_ShouldReturnHashes_WhenProvidingStrongPasswords() {

    // When
    EncodedPassword password = passwordService.encoded(strongPassword);

    // Then
    assertThat(password.getHashedValue(), is(not(equalTo(strongPassword))));
  }
}
