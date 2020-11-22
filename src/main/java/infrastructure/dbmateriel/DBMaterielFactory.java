package infrastructure.dbmateriel;

import core.Materiel;
import domain.materiel.MaterielFactory;
import infrastructure.DBexception;
import infrastructure.Database;

public class DBMaterielFactory implements MaterielFactory {

    private final Database database;

    public DBMaterielFactory(Database database) {
        this.database = database;
    }

    @Override
    public Materiel createMateriel(Materiel materiel) throws DBexception {
        return null;
    }
}
