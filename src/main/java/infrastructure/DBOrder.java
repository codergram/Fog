package infrastructure;

import domain.order.Order;
import domain.order.OrderRepository;
import domain.order.exceptions.OrderException;
import domain.order.exceptions.OrderNotFound;

import java.util.List;

public class DBOrder implements OrderRepository {
    private final Database database;
    
    public DBOrder(Database database) {
        this.database = database;
    }
    
    
    @Override
    public List<Order> getALlOrders() throws OrderNotFound {
        return null;
    }
    
    @Override
    public Order getOrderById(int id) throws OrderNotFound {
        return null;
    }
    
    @Override
    public boolean deleteOrderById(int id) throws OrderException {
        return false;
    }
    
    @Override
    public Order updateOrderStatusById(int id, Order.Status status) throws OrderException {
        return null;
    }
    
    @Override
    public Order createNewOrder(Order order) throws OrderException {
        return null;
    }
}
