package infrastructure;

import api.Api;
import api.exceptions.EmailNotSent;
import api.EmailService;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

public class JavaXEmailService implements EmailService {
    
    @Override
    public void sendEmail(String toAddress, String subject, String message, File file) throws EmailNotSent {
        Properties properties = new Properties();
        try (InputStream input = Api.class.getClassLoader().getResourceAsStream("mail/config.properties")) {
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
        Multipart multipart = new MimeMultipart();
        
    
        try {
            msg.setFrom(new InternetAddress(properties.getProperty("SENT_FROM"), properties.getProperty("SENT_FROM_NAME")));
            InternetAddress[] toAddresses = {new InternetAddress(toAddress)};
            msg.setRecipients(Message.RecipientType.TO, toAddresses);
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            
            //Bodypart for text
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(message, "text/html");
            
            
            if(file != null) {
                //Bodypart for attacthment
                BodyPart attachment = new MimeBodyPart();
                String filename = file.getAbsolutePath();
                DataSource source = new FileDataSource(filename);
                attachment.setDataHandler(new DataHandler(source));
                attachment.setFileName(file.getName());
                
                //Add multipart to bodypart.
                multipart.addBodyPart(attachment);
            }
            
            multipart.addBodyPart(messageBodyPart);
            
            
            //Sets content of message
            msg.setContent(multipart);
    
            //Sends mail
            Transport.send(msg);
            
        } catch (UnsupportedEncodingException | MessagingException e) {
            throw new EmailNotSent(e);
        }
    }
}
