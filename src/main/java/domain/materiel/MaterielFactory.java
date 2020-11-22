package domain.materiel;

import core.Materiel;
import infrastructure.DBexception;

public interface MaterielFactory {

    Materiel createMateriel(Materiel materiel) throws DBexception;

}
