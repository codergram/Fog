package domain.styk;

import core.Materiel_Træ;
import core.Styk_BeslagSkruer;
import core.Styk_Træ;

public interface StykFactory {

    Styk_Træ createStyk_Træ(Materiel_Træ materielTræ, double antal);

    Styk_BeslagSkruer createStyk_BeslagSkruer(Styk_BeslagSkruer styk_beslagSkruer, double antal);
}
