package infrastructure.dbmateriel;

import core.BeslagSkruer;
import core.Træ;
import domain.materiel.MaterielFactory;
import infrastructure.DBexception;
import infrastructure.Database;

public class DBMaterielFactory implements MaterielFactory {

    private final Database database;

    public DBMaterielFactory(Database database) {
        this.database = database;
    }

    @Override
    public Træ createTræ(Træ træ) throws DBexception {
        return null;
    }

    @Override
    public BeslagSkruer createBeslagSkruer(BeslagSkruer beslagSkruer) throws DBexception {
        return null;
    }
}
