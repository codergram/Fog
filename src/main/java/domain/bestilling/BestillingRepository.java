package domain.bestilling;

import core.Order;
import infrastructure.DBexception;

import java.util.ArrayList;

public interface BestillingRepository {

    ArrayList<Order> getAllBestillingFromDB() throws DBexception;

    Order getBestillingById(int bes_id) throws DBexception;

}
