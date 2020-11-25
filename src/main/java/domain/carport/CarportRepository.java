package domain.carport;

import infrastructure.DBException;

import java.util.ArrayList;

public interface CarportRepository {

    ArrayList<Carport> getAllCarporteFromDB() throws DBException;

    Carport getCarportById(int carport_id) throws DBException;

}
