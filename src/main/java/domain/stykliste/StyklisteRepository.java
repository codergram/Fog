package domain.stykliste;

import core.parts.Part_BeslagSkruer;
import core.parts.Part_Træ;

import java.util.ArrayList;

public interface StyklisteRepository {

    ArrayList<Part_Træ> addToStyklisteTræ(ArrayList<Part_Træ> styklisteTræ, Part_Træ styk_træ);

    ArrayList<Part_BeslagSkruer> addToStyklisteBeslagSkruer(ArrayList<Part_BeslagSkruer> styklisteBeslagSkruer, Part_BeslagSkruer styk_beslagSkruer);

}
