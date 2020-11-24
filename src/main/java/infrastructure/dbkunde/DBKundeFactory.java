package infrastructure.dbkunde;

import core.Customer;
import domain.kunde.KundeFactory;
import infrastructure.DBexception;
import infrastructure.Database;

public class DBKundeFactory implements KundeFactory {

    private final Database database;

    public DBKundeFactory(Database database) {
        this.database = database;
    }

    @Override
    public Customer createKunde(Customer customer) throws DBexception {
        return null;
    }
}
