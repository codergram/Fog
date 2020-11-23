package domain.materiel;

import core.BeslagSkruer;
import core.Træ;
import infrastructure.DBexception;

public interface MaterielFactory {

    Træ createTræ(Træ træ) throws DBexception;

    BeslagSkruer createBeslagSkruer(BeslagSkruer beslagSkruer) throws DBexception;

}
