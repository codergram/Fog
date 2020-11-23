package infrastructure.localstykliste;

import core.Stykliste_BeslagSkruer;
import core.Stykliste_Træ;
import domain.stykliste.StyklisteRepository;

import java.util.ArrayList;

public class LocalStyklisteRepository implements StyklisteRepository {


    @Override
    public ArrayList<Stykliste_Træ> addToStyklisteTræ(ArrayList<Stykliste_Træ> styklisteTræ, Stykliste_Træ stykliste_træ) {
        return null;
    }

    @Override
    public ArrayList<Stykliste_BeslagSkruer> addToStyklisteBeslagSkruer(ArrayList<Stykliste_BeslagSkruer> styklisteBeslagSkruer, Stykliste_BeslagSkruer stykliste_beslagSkruer) {
        return null;
    }
}
