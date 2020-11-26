package api.exceptions;

public class PDFNotCreated extends Exception {
    public PDFNotCreated() {
    }
    
    public PDFNotCreated(String message) {
        super(message);
    }
    
    public PDFNotCreated(String message, Throwable cause) {
        super(message, cause);
    }
    
    public PDFNotCreated(Throwable cause) {
        super(cause);
    }
    
    public PDFNotCreated(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
