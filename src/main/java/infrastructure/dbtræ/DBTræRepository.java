package infrastructure.dbtræ;

import core.Træ;
import domain.træ.TræRepository;
import infrastructure.DBexception;
import infrastructure.Database;

import java.util.ArrayList;

public class DBTræRepository implements TræRepository {

    private final Database database;

    public DBTræRepository(Database database) {
        this.database = database;
    }


    @Override
    public ArrayList<Træ> getAllTræFromDB() throws DBexception {
        return null;
    }

    @Override
    public Træ getTræById(int træ_id) throws DBexception {
        return null;
    }
}
