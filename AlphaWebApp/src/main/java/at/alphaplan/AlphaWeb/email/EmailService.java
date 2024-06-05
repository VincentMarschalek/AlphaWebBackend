package at.alphaplan.AlphaWeb.email;

import at.alphaplan.AlphaWeb.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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

  private final String VERIFICATION_LINK = "%s://%s:%s/api/registration/token?userId=%s&tokenId=%s";

  // @Async
  public void sendVerificationEmail(User user) {
    var receiver = user.getAccount().getVerificationEmail();
    var subject = getVerificationEmailSubject();
    var body = getVerificationEmailBody(user);
    mailSender.sendMail(new EmailDTO(receiver, subject, body));
  }

  public String getVerificationEmailSubject() {
    return "Click the link and verify your email to move on.";
  }

  public String getVerificationEmailBody(User user) {
    String token = user.getAccount().getVerificationEmailToken();
    return String.format(VERIFICATION_LINK, protocol, domain, port, user.getId(), token);
  }
}
