package infrastructure.dbmateriel;

import core.BeslagSkruer;
import core.Træ;
import domain.materiel.MaterielRepository;
import infrastructure.DBexception;
import infrastructure.Database;

import java.util.ArrayList;

public class DBMaterielRepository implements MaterielRepository {

    private final Database database;

    public DBMaterielRepository(Database database) {
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

    @Override
    public ArrayList<BeslagSkruer> getAllBeslagSkruerFromDB() throws DBexception {
        return null;
    }

    @Override
    public BeslagSkruer getBeslagSkruerById(int bes_id) throws DBexception {
        return null;
    }
}
