package infrastructure.dbbestilling;

import domain.bestilling.BestillingServices;
import infrastructure.DBexception;
import infrastructure.Database;

public class DBBestillingServices implements BestillingServices {

    private final Database database;

    public DBBestillingServices(Database database) {
        this.database = database;
    }

    @Override
    public void deleteBestillingById(int bes_id) throws DBexception {

    }

    @Override
    public void updatedBestillingToAfventer(int bes_id) throws DBexception {

    }

    @Override
    public void updatedBestillingToLeveret(int bes_id) throws DBexception {

    }
}
