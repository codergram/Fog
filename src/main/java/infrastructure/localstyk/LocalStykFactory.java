package infrastructure.localstyk;

import core.materials.Tree;
import core.parts.Part_BeslagSkruer;
import core.parts.Part_Træ;
import domain.styk.StykFactory;

public class LocalStykFactory implements StykFactory {
    @Override
    public Part_Træ createStyk_Træ(Tree materielTræ, double antal) {
        return null;
    }

    @Override
    public Part_BeslagSkruer createStyk_BeslagSkruer(Part_BeslagSkruer styk_beslagSkruer, double antal) {
        return null;
    }
}
