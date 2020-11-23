package infrastructure.dbkunde;

import core.Kunde;
import domain.kunde.KundeFactory;
import infrastructure.DBexception;
import infrastructure.Database;

public class DBKundeFactory implements KundeFactory {

    private final Database database;

    public DBKundeFactory(Database database) {
        this.database = database;
    }

    @Override
    public Kunde createKunde(Kunde kunde) throws DBexception {
        return null;
    }
}
