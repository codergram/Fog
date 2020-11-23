package infrastructure.localstyk;

import core.Styk_BeslagSkruer;
import core.Styk_Træ;
import core.Træ;
import domain.styk.StykFactory;

public class LocalStykFactory implements StykFactory {
    @Override
    public Styk_Træ createStyk_Træ(Træ træ, double antal) {
        return null;
    }

    @Override
    public Styk_BeslagSkruer createStyk_BeslagSkruer(Styk_BeslagSkruer styk_beslagSkruer, double antal) {
        return null;
    }
}
