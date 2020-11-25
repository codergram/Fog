package infrastructure;

import domain.carport.Carport;
import domain.carport.CarportRepository;

import java.util.ArrayList;

public class DBCarport implements CarportRepository {
    private final Database database;
    
    public DBCarport(Database database) {
        this.database = database;
    }
    
    @Override
    public ArrayList<Carport> getAllCarporteFromDB() throws DBException {
        return null;
    }
    
    @Override
    public Carport getCarportById(int carport_id) throws DBException {
        return null;
    }
}
