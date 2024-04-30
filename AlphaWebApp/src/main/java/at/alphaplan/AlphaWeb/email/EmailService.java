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

  @Value("${app.public.protocol:http}")
  private String protocol;

  @Value("${app.public.domain:localhost}")
  private String domain;

  @Value("${app.public.port:8080}")
  private String port;

  private final String VERIFICATION_LINK = "/api/registration/token?userId=%s&tokenId=%s";

  //@Async
  public void sendVerificationEmail(User user) {
    mailSender.sendMail(
        new EmailDTO(
            user.getEmail(),
            getVerificationSubject(),
            getVerificationBody(user)));
  }

  public String getVerificationSubject() {
    return "Click the link and verify your email to move on.";
  }

  public String getVerificationBody(User user) {
    return String.format(VERIFICATION_LINK, domain, user.getId());
  }
}
