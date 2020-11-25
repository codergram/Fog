package domain.styk;

import domain.material.materials.Tree;
import domain.parts.Part_BeslagSkruer;
import domain.parts.Part_Træ;

public interface StykFactory {

    Part_Træ createStyk_Træ(Tree materielTræ, double antal);

    Part_BeslagSkruer createStyk_BeslagSkruer(Part_BeslagSkruer styk_beslagSkruer, double antal);
}
