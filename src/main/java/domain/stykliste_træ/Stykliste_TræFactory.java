package domain.stykliste_træ;

import core.Træ;
import core.Stykliste_Træ;

public interface Stykliste_TræFactory {

    Stykliste_Træ createStykliste_Træ(Træ træ, double antal);
}
