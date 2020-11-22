package domain.stykliste_materiel;

import core.Materiel;
import core.Stykliste_Materiel;

public interface Stykliste_MaterielFactory {

    Stykliste_Materiel createStykliste_Materiel(Materiel materiel, double antal);
}
