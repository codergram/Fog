package domain.styk;

import core.Styk_BeslagSkruer;
import core.Styk_Træ;
import core.Træ;

public interface StykFactory {

    Styk_Træ createStyk_Træ(Træ træ, double antal);

    Styk_BeslagSkruer createStyk_BeslagSkruer(Styk_BeslagSkruer styk_beslagSkruer, double antal);
}
