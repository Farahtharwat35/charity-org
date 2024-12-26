package com.charity_org.demo.Classes.Facade;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

@Component
public class EmailFacade {
    private static EmailFacade instance;
    private final String smtpHost;
    private final String smtpPort;
    @Value("${mailgun.username}")
    private String username;
    @Value("${mailgun.password}")
    private String password;
    private final Properties properties;

    public EmailFacade() {
        smtpHost = "smtp.mailgun.org";
        smtpPort = "587";
        //get from enviornment variables

        properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", smtpPort);
    }

    public static synchronized EmailFacade getInstance() {
        if (instance == null) {
            instance = new EmailFacade();
        }
        return instance;
    }

    public boolean sendEmail(String recipient, String subject, String body) {
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(body);
            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
