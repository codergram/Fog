package domain.styk;

import core.materialer.Tree;
import core.Styk_BeslagSkruer;
import core.Styk_Træ;

public interface StykFactory {

    Styk_Træ createStyk_Træ(Tree materielTræ, double antal);

    Styk_BeslagSkruer createStyk_BeslagSkruer(Styk_BeslagSkruer styk_beslagSkruer, double antal);
}
