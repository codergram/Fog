package domain.stykliste;

import core.Styk_BeslagSkruer;
import core.Styk_Træ;

import java.util.ArrayList;

public interface StyklisteServices {

    ArrayList<Styk_Træ> resetStyklisteTræ();

    ArrayList<Styk_BeslagSkruer> resetStyklisteBeslagSkruer();

}
