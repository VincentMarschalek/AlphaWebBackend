package at.alphaplan.AlphaWeb.domain.user;

import static at.alphaplan.AlphaWeb.foundation.AssertUtil.isNotNull;
import static at.alphaplan.AlphaWeb.security.token.EmailVerificationToken.generateEmailToken;
import static java.time.Instant.now;

import at.alphaplan.AlphaWeb.security.token.EmailVerificationToken;
import at.alphaplan.AlphaWeb.security.token.TokenUtil;
import java.time.Duration;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Account {

  @Setter private boolean enabled = true;

  private EmailVerificationToken emailToken;

  public static final Duration EMAIL_VERIFICATION_DURATION = Duration.ofHours(24);

  public void generateEmailTokenFor(String email) {
    isNotNull(email, "email");
    var token = generateEmailToken(email, now().plus(EMAIL_VERIFICATION_DURATION));
    this.emailToken = token;
  }

  public String verifyEmailTokenFor(String tokenId) throws IllegalArgumentException {
    isNotNull(tokenId, "tokenId");
    isNotNull(emailToken, "token");
    TokenUtil.verifyToken(emailToken, tokenId);

    String verifiedEmail = emailToken.getVerificationEmail();

    this.emailToken = null;

    return verifiedEmail;
  }

  public String getVerificationEmail() {
    isNotNull(emailToken, "token");
    return emailToken.getVerificationEmail();
  }

  public String getVerificationEmailToken() {
    isNotNull(emailToken, "token");
    return emailToken.getVerificationEmailToken();
  }
}
