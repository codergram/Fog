package infrastructure;

import domain.materiel.materials.Options;
import domain.materiel.materials.Tree;
import domain.materiel.MaterielRepository;

import java.util.ArrayList;

public class DBMaterial implements MaterielRepository {
    private final Database database;
    
    public DBMaterial(Database database) {
        this.database = database;
    }
    
    @Override
    public ArrayList<Tree> getAllTræFromDB() throws DBException {
        return null;
    }
    
    @Override
    public Tree getTræById(int træ_id) throws DBException {
        return null;
    }
    
    @Override
    public ArrayList<Options> getAllBeslagSkruerFromDB() throws DBException {
        return null;
    }
    
    @Override
    public Options getBeslagSkruerById(int bes_id) throws DBException {
        return null;
    }
}
