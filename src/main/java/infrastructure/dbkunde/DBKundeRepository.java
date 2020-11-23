package infrastructure.dbkunde;

import core.Kunde;
import domain.kunde.KundeRepository;
import infrastructure.DBexception;
import infrastructure.Database;

import java.util.ArrayList;

public class DBKundeRepository implements KundeRepository {

    private final Database database;

    public DBKundeRepository(Database database) {
        this.database = database;
    }

    @Override
    public ArrayList<Kunde> getAllKunderFromDB() throws DBexception {
        return null;
    }

    @Override
    public Kunde getKundeById(int kunde_id) throws DBexception {
        return null;
    }
}
