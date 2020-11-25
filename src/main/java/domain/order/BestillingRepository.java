package domain.order;

import infrastructure.DBException;

import java.util.ArrayList;

public interface BestillingRepository {

    ArrayList<Order> getAllBestillingFromDB() throws DBException;

    Order getBestillingById(int bes_id) throws DBException;

}
