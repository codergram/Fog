package domain.stykliste;

import core.Stykliste_BeslagSkruer;
import core.Stykliste_Træ;

import java.util.ArrayList;

public interface StyklisteFactory {

    ArrayList<Stykliste_Træ> createStyklisteTræ();

    ArrayList<Stykliste_BeslagSkruer> createStyklisteBeslagSkruer();

}
