package infrastructure.dbtræ;

import core.Træ;
import domain.træ.TræFactory;
import infrastructure.DBexception;
import infrastructure.Database;

public class DBTræFactory implements TræFactory {

    private final Database database;

    public DBTræFactory(Database database) {
        this.database = database;
    }

    @Override
    public Træ createTræ(Træ træ) throws DBexception {
        return null;
    }
}
