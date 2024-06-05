package at.alphaplan.AlphaWeb.security;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import at.alphaplan.AlphaWeb.security.password.PasswordEncoderConfig;
import at.alphaplan.AlphaWeb.security.password.PasswordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

// @TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = {PasswordService.class})
@Import(PasswordEncoderConfig.class)
public class PasswordServiceTest {
  // 1. Test Passwort Stärke
  // FAIL: 1/2
  // SUCCESS: 3/4

  public static final String weakPassword = "password123";
  public static final String strongPassword = "AlleMeineEntchenSchwimmenInDemSee";

  @Autowired PasswordService passwordService;

  @BeforeEach
  public void setup() {}

  @Test
  public void encode_shouldThrow_whenProvidingWeakPasswords() {

    // When/Then
    assertThrows(IllegalArgumentException.class, () -> passwordService.encode(weakPassword));
  }

  @Test
  public void encode_shouldPass_whenProvidingStrongPasswords() {

    // When/Then
    passwordService.encode(strongPassword);
  }

  @Test
  public void encode_shouldReturnHashes_whenProvidingStrongPasswords() {

    // When
    PasswordService.EncodedPassword password = passwordService.encode(strongPassword);
    System.out.println(password.getHashedValue());

    // Then
    assertThat(password.getHashedValue(), is(not(equalTo(strongPassword))));
  }

  @Test
  public void encode_shouldReturnDifferentHashes_whenHashingSamePassword() {

    // When
    PasswordService.EncodedPassword password = passwordService.encode(strongPassword);
    PasswordService.EncodedPassword password2 = passwordService.encode(strongPassword);
    //    System.out.println(password.getHashedValue());
    //    System.out.println(password2.getHashedValue());

    // Then
    assertThat(password.getHashedValue(), is(not(equalTo(password2.getHashedValue()))));
  }
}
