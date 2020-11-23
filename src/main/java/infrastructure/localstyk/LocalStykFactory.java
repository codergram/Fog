package infrastructure.localstyk;

import core.Materiel_Træ;
import core.Styk_BeslagSkruer;
import core.Styk_Træ;
import domain.styk.StykFactory;

public class LocalStykFactory implements StykFactory {
    @Override
    public Styk_Træ createStyk_Træ(Materiel_Træ materielTræ, double antal) {
        return null;
    }

    @Override
    public Styk_BeslagSkruer createStyk_BeslagSkruer(Styk_BeslagSkruer styk_beslagSkruer, double antal) {
        return null;
    }
}
