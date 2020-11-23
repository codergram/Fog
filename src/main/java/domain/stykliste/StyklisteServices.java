package domain.stykliste;

import core.Stykliste_BeslagSkruer;
import core.Stykliste_Træ;

import java.util.ArrayList;

public interface StyklisteServices {

    ArrayList<Stykliste_Træ> resetStyklisteTræ();

    ArrayList<Stykliste_BeslagSkruer> resetStyklisteBeslagSkruer();

}
