package domain.carport;

import domain.carport.shed.ShedRepository;
import infrastructure.DBException;

import java.util.ArrayList;

public interface CarportRepository extends CarportFactory, ShedRepository {

    ArrayList<Carport> getAllCarporteFromDB() throws DBException;

    Carport getCarportByOrderId(int orderId) throws DBException;

}
