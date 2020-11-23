package domain.stykliste;

import core.Stykliste_BeslagSkruer;
import core.Stykliste_Træ;

import java.util.ArrayList;

public interface StyklisteRepository {

    ArrayList<Stykliste_Træ> addToStyklisteTræ(ArrayList<Stykliste_Træ> styklisteTræ, Stykliste_Træ stykliste_træ);

    ArrayList<Stykliste_BeslagSkruer> addToStyklisteBeslagSkruer(ArrayList<Stykliste_BeslagSkruer> styklisteBeslagSkruer, Stykliste_BeslagSkruer stykliste_beslagSkruer);

}
