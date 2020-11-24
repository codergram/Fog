package infrastructure.dbbestilling;

import core.Order;
import domain.bestilling.BestillingRepository;
import infrastructure.DBexception;
import infrastructure.Database;

import java.util.ArrayList;

public class DBBestillingRepository implements BestillingRepository {

    private final Database database;

    public DBBestillingRepository(Database database) {
        this.database = database;
    }

    @Override
    public ArrayList<Order> getAllBestillingFromDB() throws DBexception {
        return null;
    }

    @Override
    public Order getBestillingById(int bes_id) throws DBexception {
        return null;
    }
}
