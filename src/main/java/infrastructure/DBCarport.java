package infrastructure;

import domain.carport.Carport;
import domain.carport.CarportRepository;
import domain.carport.shed.Shed;
import domain.carport.shed.ShedException;
import infrastructure.exceptions.DBException;

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
    public Carport getCarportByOrderId(int orderId) throws DBException {
        return null;
    }
    
    @Override
    public Carport createCarport(Carport carport) throws DBException {
        return null;
    }
    
    @Override
    public ArrayList<Shed> getAllSheds() throws ShedException {
        return null;
    }
    
    @Override
    public Shed getShedByOrderId(int orderId) throws ShedException {
        return null;
    }
    
    @Override
    public Shed createShed(Shed shed) throws ShedException {
        return null;
    }
}
