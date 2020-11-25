package api.exceptions;

public class EmailNotSent extends Exception {
    public EmailNotSent() {
    }
    
    public EmailNotSent(String message) {
        super(message);
    }
    
    public EmailNotSent(String message, Throwable cause) {
        super(message, cause);
    }
    
    public EmailNotSent(Throwable cause) {
        super(cause);
    }
}
