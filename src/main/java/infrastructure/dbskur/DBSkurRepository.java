package infrastructure.dbskur;

import core.Skur;
import domain.skur.SkurRepository;
import infrastructure.DBexception;
import infrastructure.Database;

import java.util.ArrayList;

public class DBSkurRepository implements SkurRepository {

    private final Database database;

    public DBSkurRepository(Database database) {
        this.database = database;
    }

    @Override
    public ArrayList<Skur> getAllSkurFromDB() throws DBexception {
        return null;
    }

    @Override
    public Skur getSkurById(int skur_id) throws DBexception {
        return null;
    }
}
