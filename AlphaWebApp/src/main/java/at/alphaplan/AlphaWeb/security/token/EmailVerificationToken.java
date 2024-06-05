package at.alphaplan.AlphaWeb.security.token;

import static at.alphaplan.AlphaWeb.security.token.TokenUtil.generateToken;

import java.time.Instant;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.Transient;

@SuperBuilder
@Getter
public class EmailVerificationToken extends Token {

  private final String verificationEmail;

  @Transient private String verificationEmailToken;

  @PersistenceCreator
  public EmailVerificationToken(
      String verificationEmail, String encodedValue, Instant createdAt, Instant expiresAt) {
    super(encodedValue, createdAt, expiresAt);
    this.verificationEmail = verificationEmail;
  }

  public static EmailVerificationToken generateEmailToken(String email, Instant expiresAt) {
    // Generate a secure 128-bit token as UUIDv4 and hash it with Keccak-256.
    var tokenValues = generateToken();

    // Build the token object with the builder design pattern
    return EmailVerificationToken.builder()
        .encodedValue(tokenValues.hashedTokenIdBase64())
        .verificationEmail(email)
        .verificationEmailToken(tokenValues.tokenId())
        .createdAt(Instant.now())
        .expiresAt(expiresAt)
        .build();
  }
}
