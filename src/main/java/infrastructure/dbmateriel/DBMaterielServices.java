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
    public void updateTræById(int træ_id, String træ_navn, double træ_pris) throws DBexception {

    }

    @Override
    public void updateBeslagSkruerById(int bes_id, String bes_navn, double bes_pris) throws DBexception {

    }
}
