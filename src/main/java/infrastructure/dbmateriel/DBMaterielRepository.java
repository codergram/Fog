package infrastructure.dbmateriel;

import core.Materiel;
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
    public ArrayList<Materiel> getAllMaterielFromDB() throws DBexception {
        return null;
    }

    @Override
    public Materiel getMaterielById(int mat_id) throws DBexception {
        return null;
    }
}
