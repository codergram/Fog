package infrastructure.dbbeslagskruer;

import domain.beslagskruer.BeslagSkruerServices;
import infrastructure.DBexception;
import infrastructure.Database;

public class DBBeslagSkruerServices implements BeslagSkruerServices {

    private final Database database;

    public DBBeslagSkruerServices(Database database) {
        this.database = database;
    }

    @Override
    public void updateBeslagSkruerById(int bes_id, String bes_navn, double bes_pris) throws DBexception {

    }
}
