package infrastructure.localstykliste;

import core.Stykliste_BeslagSkruer;
import core.Stykliste_Træ;
import domain.stykliste.StyklisteFactory;

import java.util.ArrayList;

public class LocalStyklisteFactory implements StyklisteFactory {

    @Override
    public ArrayList<Stykliste_Træ> createStyklisteTræ() {
        return null;
    }

    @Override
    public ArrayList<Stykliste_BeslagSkruer> createStyklisteBeslagSkruer() {
        return null;
    }
}
