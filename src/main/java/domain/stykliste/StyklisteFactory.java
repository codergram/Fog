package domain.stykliste;

import domain.parts.Part_BeslagSkruer;
import domain.parts.Part_Træ;

import java.util.ArrayList;

public interface StyklisteFactory {

    ArrayList<Part_Træ> createStyklisteTræ();

    ArrayList<Part_BeslagSkruer> createStyklisteBeslagSkruer();

}
