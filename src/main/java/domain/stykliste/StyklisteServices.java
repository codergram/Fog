package domain.stykliste;

import domain.parts.Part_BeslagSkruer;
import domain.parts.Part_Træ;

import java.util.ArrayList;

public interface StyklisteServices {

    ArrayList<Part_Træ> resetStyklisteTræ();

    ArrayList<Part_BeslagSkruer> resetStyklisteBeslagSkruer();

}
