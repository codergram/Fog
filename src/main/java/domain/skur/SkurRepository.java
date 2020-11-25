package domain.skur;

import infrastructure.DBException;

import java.util.ArrayList;

public interface SkurRepository {

    ArrayList<Skur> getAllSkurFromDB() throws DBException;

    Skur getSkurById(int skur_id) throws DBException;

}
