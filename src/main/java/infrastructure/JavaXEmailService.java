package infrastructure;

import api.Api;
import api.exceptions.EmailNotSent;
import api.EmailService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

public class JavaXEmailService implements EmailService {
    
    @Override
    public void sendEmail(String toAddress, String subject, String message) throws EmailNotSent {
        Properties properties = new Properties();
        try (InputStream input = Api.class.getClassLoader().getResourceAsStream("config.properties")) {
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    
        Authenticator auth = new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(properties.getProperty("USERNAME"), properties.getProperty("PASSWORD"));
            }
        };
    
        Session session = Session.getInstance(properties, auth);
    
        Message msg = new MimeMessage(session);
    
        try {
            msg.setFrom(new InternetAddress(properties.getProperty("SENT_FROM"), properties.getProperty("SENT_FROM_NAME")));
            InternetAddress[] toAddresses = {new InternetAddress(toAddress)};
            msg.setRecipients(Message.RecipientType.TO, toAddresses);
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            msg.setContent(message, "text/html");
    
            Transport.send(msg);
        } catch (UnsupportedEncodingException | MessagingException e) {
            throw new EmailNotSent(e);
        }
    }
}
