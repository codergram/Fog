package domain.materiel;

import core.Materiel_BeslagSkruer;
import core.Materiel_Træ;
import infrastructure.DBexception;

import java.util.ArrayList;

public interface MaterielRepository {

    ArrayList<Materiel_Træ> getAllTræFromDB() throws DBexception;

    Materiel_Træ getTræById(int træ_id) throws DBexception;

    ArrayList<Materiel_BeslagSkruer> getAllBeslagSkruerFromDB() throws DBexception;

    Materiel_BeslagSkruer getBeslagSkruerById(int bes_id) throws DBexception;

}
