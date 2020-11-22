package domain.stykliste;

import core.Stykliste_Materiel;

import java.util.ArrayList;

public interface StyklisteRepository {

    ArrayList<Stykliste_Materiel> addToStykliste(ArrayList<Stykliste_Materiel> stykliste, Stykliste_Materiel stykliste_materiel);

}
