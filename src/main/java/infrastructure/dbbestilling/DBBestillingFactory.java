package infrastructure.dbbestilling;

import core.Bestilling;
import domain.bestilling.BestillingFactory;
import infrastructure.DBexception;
import infrastructure.Database;

public class DBBestillingFactory implements BestillingFactory {

    private final Database database;

    public DBBestillingFactory(Database database) {
        this.database = database;
    }

    @Override
    public Bestilling createBestilling(Bestilling bestilling) throws DBexception {
        return null;
    }
}
