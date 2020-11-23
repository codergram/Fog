package domain.stykliste_beslagskruer;

import core.Stykliste_BeslagSkruer;
import core.Stykliste_Træ;
import core.Træ;

public interface Stykliste_BeslagSkruerFactory {

    Stykliste_BeslagSkruer createStykliste_BeslagSkruer(Stykliste_BeslagSkruer stykliste_beslagSkruer, double antal);
}
