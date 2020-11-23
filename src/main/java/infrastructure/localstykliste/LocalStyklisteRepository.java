package infrastructure.localstykliste;

import core.Styk_BeslagSkruer;
import core.Styk_Træ;
import domain.stykliste.StyklisteRepository;

import java.util.ArrayList;

public class LocalStyklisteRepository implements StyklisteRepository {


    @Override
    public ArrayList<Styk_Træ> addToStyklisteTræ(ArrayList<Styk_Træ> styklisteTræ, Styk_Træ styk_træ) {
        return null;
    }

    @Override
    public ArrayList<Styk_BeslagSkruer> addToStyklisteBeslagSkruer(ArrayList<Styk_BeslagSkruer> styklisteBeslagSkruer, Styk_BeslagSkruer styk_beslagSkruer) {
        return null;
    }
}
