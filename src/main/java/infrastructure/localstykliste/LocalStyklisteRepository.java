package infrastructure.localstykliste;

import core.parts.Part_BeslagSkruer;
import core.parts.Part_Træ;
import domain.stykliste.StyklisteRepository;

import java.util.ArrayList;

public class LocalStyklisteRepository implements StyklisteRepository {


    @Override
    public ArrayList<Part_Træ> addToStyklisteTræ(ArrayList<Part_Træ> styklisteTræ, Part_Træ styk_træ) {
        return null;
    }

    @Override
    public ArrayList<Part_BeslagSkruer> addToStyklisteBeslagSkruer(ArrayList<Part_BeslagSkruer> styklisteBeslagSkruer, Part_BeslagSkruer styk_beslagSkruer) {
        return null;
    }
}
