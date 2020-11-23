package domain.træ;

import core.Træ;
import infrastructure.DBexception;

import java.util.ArrayList;

public interface TræRepository {

    ArrayList<Træ> getAllTræFromDB() throws DBexception;

    Træ getTræById(int træ_id) throws DBexception;

}
