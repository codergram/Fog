package api;

import api.exceptions.EmailNotSent;

public interface EmailService {
    void sendEmail(String toAddress, String subject, String message) throws EmailNotSent;
}
