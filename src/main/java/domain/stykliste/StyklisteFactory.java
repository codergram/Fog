package domain.stykliste;

import core.parts.Part_BeslagSkruer;
import core.parts.Part_Træ;

import java.util.ArrayList;

public interface StyklisteFactory {

    ArrayList<Part_Træ> createStyklisteTræ();

    ArrayList<Part_BeslagSkruer> createStyklisteBeslagSkruer();

}
