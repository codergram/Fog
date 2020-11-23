package infrastructure.dbmateriel;

import core.Materiel_BeslagSkruer;
import core.Materiel_Træ;
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
    public ArrayList<Materiel_Træ> getAllTræFromDB() throws DBexception {
        return null;
    }

    @Override
    public Materiel_Træ getTræById(int træ_id) throws DBexception {
        return null;
    }

    @Override
    public ArrayList<Materiel_BeslagSkruer> getAllBeslagSkruerFromDB() throws DBexception {
        return null;
    }

    @Override
    public Materiel_BeslagSkruer getBeslagSkruerById(int bes_id) throws DBexception {
        return null;
    }
}
