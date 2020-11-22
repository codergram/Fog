package infrastructure.dbskur;

import core.Skur;
import domain.skur.SkurFactory;
import infrastructure.DBexception;
import infrastructure.Database;

public class DBSkurFactory implements SkurFactory {

    private final Database database;

    public DBSkurFactory(Database database) {
        this.database = database;
    }

    @Override
    public Skur createSkur(Skur skur) throws DBexception {
        return null;
    }
}
