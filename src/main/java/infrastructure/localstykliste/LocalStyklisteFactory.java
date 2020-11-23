package infrastructure.localstykliste;

import core.Styk_BeslagSkruer;
import core.Styk_Træ;
import domain.stykliste.StyklisteFactory;

import java.util.ArrayList;

public class LocalStyklisteFactory implements StyklisteFactory {

    @Override
    public ArrayList<Styk_Træ> createStyklisteTræ() {
        return null;
    }

    @Override
    public ArrayList<Styk_BeslagSkruer> createStyklisteBeslagSkruer() {
        return null;
    }
}
