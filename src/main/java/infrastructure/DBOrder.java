package infrastructure;

import domain.order.Order;
import domain.order.OrderRepository;

import java.util.ArrayList;

public class DBOrder implements OrderRepository {
    private final Database database;
    
    public DBOrder(Database database) {
        this.database = database;
    }
    
    @Override
    public ArrayList<Order> getAllBestillingFromDB() throws DBException {
        return null;
    }
    
    @Override
    public Order getBestillingById(int bes_id) throws DBException {
        return null;
    }
}
