package domain.materiel;

import core.Materiel_BeslagSkruer;
import core.Materiel_Træ;
import infrastructure.DBexception;

public interface MaterielFactory {

    Materiel_Træ createTræ(Materiel_Træ materielTræ) throws DBexception;

    Materiel_BeslagSkruer createBeslagSkruer(Materiel_BeslagSkruer materielBeslagSkruer) throws DBexception;

}
