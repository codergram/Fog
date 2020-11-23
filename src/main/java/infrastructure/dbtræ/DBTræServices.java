package infrastructure.dbtræ;

import domain.træ.TræServices;
import infrastructure.DBexception;
import infrastructure.Database;

public class DBTræServices implements TræServices {

    private final Database database;

    public DBTræServices(Database database) {
        this.database = database;
    }

    @Override
    public void updateTræById(int træ_id, String træ_navn, double træ_pris) throws DBexception {

    }
}
