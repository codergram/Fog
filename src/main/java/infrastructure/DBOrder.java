package infrastructure;

import domain.customer.Customer;
import domain.order.Order;
import domain.order.OrderRepository;
import domain.order.exceptions.OrderException;
import domain.order.exceptions.OrderNotFound;
import domain.user.User;

import java.util.ArrayList;
import java.util.List;

public class DBOrder implements OrderRepository {
    private final Database database;
    
    public DBOrder(Database database) {
        this.database = database;
    }
    
    
    @Override
    public List<Order> getALlOrders() throws OrderNotFound {
        //TODO: MySQL implementation
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(1,400,500,null,
                new User(1,"Emil", "emil@emil.dk", User.Role.Admin),
                new Customer(1,"Kurt Verner", "Vejnavn 1", 1234, "Bynavn", 12345678, "kurt@verner.dk"),
                Order.Status.New,
                null));
        return orders;
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
