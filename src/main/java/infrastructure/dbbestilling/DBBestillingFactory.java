package infrastructure.dbbestilling;

import core.Order;
import domain.bestilling.BestillingFactory;
import infrastructure.DBexception;
import infrastructure.Database;

public class DBBestillingFactory implements BestillingFactory {

    private final Database database;

    public DBBestillingFactory(Database database) {
        this.database = database;
    }

    @Override
    public Order createBestilling(Order order) throws DBexception {
        return null;
    }
}
