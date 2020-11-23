package domain.materiel;

import core.BeslagSkruer;
import core.Træ;
import infrastructure.DBexception;

import java.util.ArrayList;

public interface MaterielRepository {

    ArrayList<Træ> getAllTræFromDB() throws DBexception;

    Træ getTræById(int træ_id) throws DBexception;

    ArrayList<BeslagSkruer> getAllBeslagSkruerFromDB() throws DBexception;

    BeslagSkruer getBeslagSkruerById(int bes_id) throws DBexception;

}
