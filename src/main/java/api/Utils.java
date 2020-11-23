package api;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Utils {
    public static String removeHtml(String src) {
        return src.replaceAll("\"[^\"]*+\"", "");
    }
    
    /**
     * A utility methode for sending e-mail messages
     * @author www.codejava.net
     *
     */
    public static void sendEmail(String toAddress, String subject, String message) throws AddressException,
            MessagingException, UnsupportedEncodingException {
        
        Properties properties = new Properties();
        try (InputStream input = Api.class.getClassLoader().getResourceAsStream("config.properties")) {
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(properties.getProperty("USERNAME"), properties.getProperty("PASSWORD"));
            }
        };
        
        Session session = Session.getInstance(properties, auth);
        
        Message msg = new MimeMessage(session);
        
        msg.setFrom(new InternetAddress(properties.getProperty("SENT_FROM"), properties.getProperty("SENT_FROM_NAME")));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        msg.setContent(message, "text/html");
        
        Transport.send(msg);
    }
    
    public static String fileToString(String file){
        if(file == null || file.isEmpty()) return null;
        try (InputStream input = Api.class.getClassLoader().getResourceAsStream(file)) {
            return IOUtils.toString(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
