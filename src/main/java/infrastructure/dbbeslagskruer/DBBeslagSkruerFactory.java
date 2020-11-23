package infrastructure.dbbeslagskruer;

import core.BeslagSkruer;
import domain.beslagskruer.BeslagSkruerFactory;
import infrastructure.DBexception;
import infrastructure.Database;

public class DBBeslagSkruerFactory implements BeslagSkruerFactory {

    private final Database database;

    public DBBeslagSkruerFactory(Database database) {
        this.database = database;
    }

    @Override
    public BeslagSkruer createBeslagSkruer(BeslagSkruer beslagSkruer) throws DBexception {
        return null;
    }
}
