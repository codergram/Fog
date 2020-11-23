package infrastructure.dbcarport;

import core.Carport;
import domain.carport.CarportRepository;
import infrastructure.DBexception;
import infrastructure.Database;

import java.util.ArrayList;

public class DBCarportRepository implements CarportRepository {

    private final Database database;

    public DBCarportRepository(Database database) {
        this.database = database;
    }

    @Override
    public ArrayList<Carport> getAllCarporteFromDB() throws DBexception {
        return null;
    }

    @Override
    public Carport getCarportById(int carport_id) throws DBexception {
        return null;
    }
}
