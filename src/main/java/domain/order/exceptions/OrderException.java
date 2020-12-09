package domain.order.exceptions;

public class OrderException extends Exception {
    public OrderException() {
        super("Order error");
    }
    
    public OrderException(String message) {
        super(message);
    }
    
    public OrderException(Throwable cause) {
        super(cause);
    }
}
