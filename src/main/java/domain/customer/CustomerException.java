package domain.customer;

public class CustomerException extends Exception {
    public CustomerException() {
    }
    
    public CustomerException(String message) {
        super(message);
    }
}
