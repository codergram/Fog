package domain.carport;

import core.Carport;
import infrastructure.DBexception;

import java.util.ArrayList;

public interface CarportRepository {

    ArrayList<Carport> getAllCarporteFromDB() throws DBexception;

    Carport getCarportById(int carport_id) throws DBexception;

}
