package infrastructure.localstykliste;

import core.parts.Part_BeslagSkruer;
import core.parts.Part_Træ;
import domain.stykliste.StyklisteFactory;

import java.util.ArrayList;

public class LocalStyklisteFactory implements StyklisteFactory {

    @Override
    public ArrayList<Part_Træ> createStyklisteTræ() {
        return null;
    }

    @Override
    public ArrayList<Part_BeslagSkruer> createStyklisteBeslagSkruer() {
        return null;
    }
}
