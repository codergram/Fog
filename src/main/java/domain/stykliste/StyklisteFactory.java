package domain.stykliste;

import core.Styk_BeslagSkruer;
import core.Styk_Træ;

import java.util.ArrayList;

public interface StyklisteFactory {

    ArrayList<Styk_Træ> createStyklisteTræ();

    ArrayList<Styk_BeslagSkruer> createStyklisteBeslagSkruer();

}
