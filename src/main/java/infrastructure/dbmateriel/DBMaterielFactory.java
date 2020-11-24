package infrastructure.dbmateriel;

import core.materials.Options;
import core.materials.Tree;
import domain.materiel.MaterielFactory;
import infrastructure.DBexception;
import infrastructure.Database;

public class DBMaterielFactory implements MaterielFactory {

    private final Database database;

    public DBMaterielFactory(Database database) {
        this.database = database;
    }

    @Override
    public Tree createTræ(Tree materielTræ) throws DBexception {
        return null;
    }

    @Override
    public Options createBeslagSkruer(Options materielBeslagSkruer) throws DBexception {
        return null;
    }
}
