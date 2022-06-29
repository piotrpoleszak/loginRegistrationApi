package com.piotrpoleszak.loginAndRegistration.email;

import com.piotrpoleszak.loginAndRegistration.exception.CoreException;
import com.piotrpoleszak.loginAndRegistration.exception.ErrorCode;
import com.piotrpoleszak.loginAndRegistration.exception.ErrorSubcode;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

import static com.piotrpoleszak.loginAndRegistration.exception.ErrorCode.VALIDATE_ERROR;
import static com.piotrpoleszak.loginAndRegistration.exception.ErrorSubcode.EMAIL_SEND_FAILED;

@Service
@AllArgsConstructor
public class EmailService implements EmailSender {

    private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    private final JavaMailSender mailSender;

    @Override
    @Async
    public void send(String to, String email) {
        try {
            var mimeMessage = mailSender.createMimeMessage();
            var helper = new MimeMessageHelper(mimeMessage, "utf-8");

            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject("Confirm your email");
            helper.setFrom("piotrpoleszak00@gmail.com");

        } catch (MessagingException e) {
            LOGGER.error("Email send fail", e);
            throw new CoreException(VALIDATE_ERROR, EMAIL_SEND_FAILED);
        }
    }
}
