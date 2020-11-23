package domain.stykliste;

import core.Styk_BeslagSkruer;
import core.Styk_Træ;

import java.util.ArrayList;

public interface StyklisteRepository {

    ArrayList<Styk_Træ> addToStyklisteTræ(ArrayList<Styk_Træ> styklisteTræ, Styk_Træ styk_træ);

    ArrayList<Styk_BeslagSkruer> addToStyklisteBeslagSkruer(ArrayList<Styk_BeslagSkruer> styklisteBeslagSkruer, Styk_BeslagSkruer styk_beslagSkruer);

}
