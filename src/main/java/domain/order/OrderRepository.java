package domain.order;

import domain.order.exceptions.OrderException;
import domain.order.exceptions.OrderNotFound;

import java.util.List;

public interface OrderRepository extends OrderFactory {

    List<Order> getALlOrders() throws OrderNotFound;
    Order getOrderById(int id) throws OrderNotFound;
    
    boolean deleteOrderById(int id) throws OrderException;
    Order updateOrderStatusById(int id, Order.Status status) throws OrderException;
    
    

}
