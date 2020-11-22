package infrastructure.dbbestilling;

import core.Bestilling;
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
    public ArrayList<Bestilling> getAllBestillingFromDB() throws DBexception {
        return null;
    }

    @Override
    public Bestilling getBestillingById(int bes_id) throws DBexception {
        return null;
    }
}
