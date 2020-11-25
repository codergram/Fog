package domain.carport.shed;

import infrastructure.DBException;

import java.util.ArrayList;

public interface ShedRepository extends ShedFactory{

    ArrayList<Shed> getAllSkurFromDB() throws DBException;

    Shed getSkurById(int skur_id) throws DBException;

}
