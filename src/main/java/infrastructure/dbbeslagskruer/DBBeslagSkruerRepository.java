package infrastructure.dbbeslagskruer;

import core.BeslagSkruer;
import domain.beslagskruer.BeslagSkruerRepository;
import infrastructure.DBexception;
import infrastructure.Database;

import java.util.ArrayList;

public class DBBeslagSkruerRepository implements BeslagSkruerRepository {

    private final Database database;

    public DBBeslagSkruerRepository(Database database) {
        this.database = database;
    }

    @Override
    public ArrayList<BeslagSkruer> getAllBeslagSkruerFromDB() throws DBexception {
        return null;
    }

    @Override
    public BeslagSkruer getBeslagSkruerById(int bes_id) throws DBexception {
        return null;
    }
}
