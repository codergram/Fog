package domain.stykliste;

import core.parts.Part_BeslagSkruer;
import core.parts.Part_Træ;

import java.util.ArrayList;

public interface StyklisteServices {

    ArrayList<Part_Træ> resetStyklisteTræ();

    ArrayList<Part_BeslagSkruer> resetStyklisteBeslagSkruer();

}
