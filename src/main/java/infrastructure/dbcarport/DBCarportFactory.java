package infrastructure.dbcarport;

import core.Carport;
import domain.carport.CarportFactory;
import infrastructure.DBexception;
import infrastructure.Database;

public class DBCarportFactory implements CarportFactory {

    private final Database database;

    public DBCarportFactory(Database database) {
        this.database = database;
    }

    @Override
    public Carport createCarport(Carport carport) throws DBexception {
        return null;
    }
}
