package domain.beslagskruer;

import core.BeslagSkruer;
import infrastructure.DBexception;

import java.util.ArrayList;

public interface BeslagSkruerRepository {

    ArrayList<BeslagSkruer> getAllBeslagSkruerFromDB() throws DBexception;

    BeslagSkruer getBeslagSkruerById(int bes_id) throws DBexception;
}
