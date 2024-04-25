package at.alphaplan.AlphaWeb.email;

import at.alphaplan.AlphaWeb.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
  private final IMailSender mailSender;

  @Value("${domain}")
  private String domain;

  private final String VERIFICATION_LINK = "%s/api/registration/verify/userId=%s&tokenId=%s";

  @Async
  public void sendVerificationEmail(User user) {
    mailSender.sendMail(
        new EmailDTO(
            user.getEmail(),
            getVerificationSubject(),
            getVerificationBody(user, user.getAccount().getTokenId())));
  }

  public String getVerificationSubject() {
    return "Click the link and verify your email to move on.";
  }

  public String getVerificationBody(User user, String tokenId) {
    return String.format(VERIFICATION_LINK, domain, user.getId(), tokenId);
  }
}
