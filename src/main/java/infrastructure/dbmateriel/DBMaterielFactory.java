package infrastructure.dbmateriel;

import core.Materiel_BeslagSkruer;
import core.Materiel_Træ;
import domain.materiel.MaterielFactory;
import infrastructure.DBexception;
import infrastructure.Database;

public class DBMaterielFactory implements MaterielFactory {

    private final Database database;

    public DBMaterielFactory(Database database) {
        this.database = database;
    }

    @Override
    public Materiel_Træ createTræ(Materiel_Træ materielTræ) throws DBexception {
        return null;
    }

    @Override
    public Materiel_BeslagSkruer createBeslagSkruer(Materiel_BeslagSkruer materielBeslagSkruer) throws DBexception {
        return null;
    }
}
