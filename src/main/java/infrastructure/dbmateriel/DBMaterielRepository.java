package infrastructure.dbmateriel;

import core.materials.Options;
import core.materials.Tree;
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
    public ArrayList<Tree> getAllTræFromDB() throws DBexception {
        return null;
    }

    @Override
    public Tree getTræById(int træ_id) throws DBexception {
        return null;
    }

    @Override
    public ArrayList<Options> getAllBeslagSkruerFromDB() throws DBexception {
        return null;
    }

    @Override
    public Options getBeslagSkruerById(int bes_id) throws DBexception {
        return null;
    }
}
