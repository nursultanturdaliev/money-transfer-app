package com.nursultanturdaliev.moneytransferapp.services;

import com.nursultanturdaliev.moneytransferapp.HomeController;
import com.nursultanturdaliev.moneytransferapp.model.MailProperties;
import com.nursultanturdaliev.moneytransferapp.model.User;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class SendingMailService {
    private final MailProperties mailProperties;

    private SpringTemplateEngine springTemplateEngine;

    private org.slf4j.Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    SendingMailService(MailProperties mailProperties, SpringTemplateEngine springTemplateEngine) {
        this.mailProperties = mailProperties;
        this.springTemplateEngine = springTemplateEngine;
    }

    public void sendVerificationMail(User user, String verificationCode) {
        String subject = "Please verify your email";
        String body = "";
        try {

            Context context = new Context();
            context.setVariable("verificationURL", mailProperties.getVerificationapi() + verificationCode);
            context.setVariable("user", user);
            body = springTemplateEngine.process("email-verification.html", context);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }

        sendMail(user.getEmail(), subject, body);
    }

    public void sendPasswordResetTokenMail(User user, String resetToken) {
        String subject = "Please reset token";
        String body = "";
        try {
            Context context = new Context();
            context.setVariable("resetTokenURL", mailProperties.getResetpasswordapi() + resetToken);
            context.setVariable("user", user);
            body = springTemplateEngine.process("password-reset-token.html",context);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }

        sendMail(user.getEmail(),subject,body);
    }

    private boolean sendMail(String toEmail, String subject, String body) {
        try {
            Properties props = System.getProperties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.port", mailProperties.getSmtp().getPort());
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(props);
            session.setDebug(true);

            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(mailProperties.getFrom(), mailProperties.getFromName()));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            msg.setSubject(subject);
            msg.setContent(body, "text/html");

            Transport transport = session.getTransport();
            transport.connect(mailProperties.getSmtp().getHost(), mailProperties.getSmtp().getUsername(), mailProperties.getSmtp().getPassword());
            transport.sendMessage(msg, msg.getAllRecipients());
            return true;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }

        return false;
    }
}
