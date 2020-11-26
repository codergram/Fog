package api;

import api.exceptions.EmailNotSent;

import java.io.File;

public interface EmailService {
    void sendEmail(String toAddress, String subject, String message, File file) throws EmailNotSent;
}
