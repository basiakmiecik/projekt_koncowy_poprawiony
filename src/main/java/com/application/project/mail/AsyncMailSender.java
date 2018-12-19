package com.application.project.mail;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class AsyncMailSender {

    private final Logger logger = LoggerFactory.getLogger(AsyncMailSender.class);

    private JavaMailSenderImpl javaMailSender;

    private MailProperties mailProperties;

    public AsyncMailSender(JavaMailSenderImpl javaMailSender, MailProperties mailProperties) {
        this.javaMailSender = javaMailSender;
        this.mailProperties = mailProperties;
    }

    @Async
    public void sendEmail(String to, String subject, String content) {
        sendEmailWithAttachment(to, subject, content, null, null);
    }


    @Async
    public void sendEmailWithAttachment(String to, String subject, String content, String attachmentName, File attachment) {
        logger.debug("Send e-mail[html '{}'] to '{}' with subject '{}'", content, to, subject);

        // Prepare message using a Spring helper
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            String fromEmail = "basia_km@interia.pl";
            String fromName = "Aplikacja do finansów";
            String replyTo = "basia_km@interia.pl";

            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            message.setTo(to);
            message.setFrom(fromEmail, fromName);
            message.setSubject(subject);
            message.setReplyTo(replyTo);
            message.setText(content, true);
            if(attachmentName != null) {
                message.addAttachment(attachmentName, attachment);
            }

            javaMailSender.send(mimeMessage);

            saveEmailToSendDir(mimeMessage);

            logger.debug("Sent e-mail to User '{}'", to);
        } catch (Exception e) {
            logger.warn("E-mail could not be sent to user '{}', exception is: {}", to, e.getMessage());
        }
    }

    private void saveEmailToSendDir(MimeMessage mimeMessage) throws MessagingException {
        Session session = javaMailSender.getSession();

        Store store = session.getStore("imap");
        String host = mailProperties.getHost();
        String username = mailProperties.getUsername();
        String password = mailProperties.getPassword();

        store.connect(host, username, password);

        Folder folder = store.getFolder("INBOX.Sent");
        folder.open(Folder.READ_WRITE);
        mimeMessage.setFlag(Flags.Flag.SEEN, true);
        folder.appendMessages(new Message[]{mimeMessage});

        store.close();
    }
}

