package domain.styk;

import core.materials.Tree;
import core.parts.Part_BeslagSkruer;
import core.parts.Part_Træ;

public interface StykFactory {

    Part_Træ createStyk_Træ(Tree materielTræ, double antal);

    Part_BeslagSkruer createStyk_BeslagSkruer(Part_BeslagSkruer styk_beslagSkruer, double antal);
}
