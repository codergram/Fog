package domain.skur;

import core.Skur;
import infrastructure.DBexception;

import java.util.ArrayList;

public interface SkurRepository {

    ArrayList<Skur> getAllSkurFromDB() throws DBexception;

    Skur getSkurById(int skur_id) throws DBexception;

}
