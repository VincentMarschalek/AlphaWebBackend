package at.alphaplan.AlphaWeb.email;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnBean(JavaMailSender.class)
@RequiredArgsConstructor
public class JavaMailSenderImpl implements IMailSender
{
    public static final String LOG_EMAIL= "Sending email with JavaMailSenderImpl to \nRECIPIENT: {}";
    private final Logger LOGGER = LoggerFactory.getLogger(JavaMailSenderImpl.class);

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private final JavaMailSender mailSender;

    public void sendMail(EmailDTO emailDTO)
    {
        try
        {
            LOGGER.info(LOG_EMAIL, emailDTO.recipient());

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(emailDTO.recipient());
            mailMessage.setSubject(emailDTO.subject());
            mailMessage.setText(emailDTO.body());
            mailSender.send(mailMessage);
        }
        catch (MailException e)
        {
            throw new RuntimeException("Failed to send email", e);
        }
    }
}
