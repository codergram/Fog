package infrastructure.dbmateriel;

import domain.materiel.MaterielServices;
import infrastructure.DBexception;
import infrastructure.Database;

public class DBMaterielServices implements MaterielServices {

    private final Database database;

    public DBMaterielServices(Database database) {
        this.database = database;
    }

    @Override
    public void updateMaterielById(int mat_id, String mat_navn, double mat_pris) throws DBexception {

    }
}
