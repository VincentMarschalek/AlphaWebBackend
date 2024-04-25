package at.alphaplan.AlphaWeb.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnMissingBean(JavaMailSender.class)
public class LoggerMailSenderImpl implements IMailSender
{
    private final Logger LOGGER = LoggerFactory.getLogger(LoggerMailSenderImpl.class);

    public static final String LOG_EMAIL_INFO =
            "Logging email with LoggerMailSenderImpl to \nRECIPIENT: {}";
    public static final String LOG_EMAIL_BODY =
            "RECIPIENT: {}\nSUBJECT: {}\nBODY: {}";
    @Override
    public void sendMail(EmailDTO emailDTO)
    {
        LOGGER.info("Sending E-Mail via LoggerMailSenderImpl");
        LOGGER.info("RECIPIENT: {}\nSUBJECT: {}\nBODY: {}", emailDTO.recipient(), emailDTO.subject(), emailDTO.body());
    }
}
