package domain.order.exceptions;

public class OrderNotFound extends Exception{
    public OrderNotFound() {
        super("Order not found");
    }
    
    public OrderNotFound(String message) {
        super(message);
    }
}
