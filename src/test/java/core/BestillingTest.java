package core;

import org.junit.Test;
import org.junit.jupiter.api.Tag;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BestillingTest {

    @org.junit.jupiter.api.Test
    void calculate_orderTotal() {

        Materiel materiel1 = new Materiel("test_product", 10);
        Materiel materiel2 = new Materiel("test_product", 20);
        Materiel materiel3 = new Materiel("test_product", 20.5);
        Materiel materiel4 = new Materiel("test_product", 21.5);

        int antal = 1;
        Stykliste_Materiel stykliste_materiel1 = new Stykliste_Materiel(materiel1, antal);
        Stykliste_Materiel stykliste_materiel2 = new Stykliste_Materiel(materiel2, antal);
        Stykliste_Materiel stykliste_materiel3 = new Stykliste_Materiel(materiel3, antal);
        Stykliste_Materiel stykliste_materiel4 = new Stykliste_Materiel(materiel4, antal);

        ArrayList<Stykliste_Materiel> styklisteMateriel = new ArrayList<>();
        styklisteMateriel.add(stykliste_materiel1);
        styklisteMateriel.add(stykliste_materiel2);
        styklisteMateriel.add(stykliste_materiel3);
        styklisteMateriel.add(stykliste_materiel4);

        Stykliste stykliste = new Stykliste(styklisteMateriel);

        Kunde kunde = new Kunde("", "", 0, "", 0, "");
        Carport carport = new Carport("", 0, 0);
        Skur skur = new Skur(0, 0);

        Bestilling bestilling = new Bestilling("", "", 25, stykliste, kunde, carport, skur);

        assertEquals(72, bestilling.getBes_subtotal());
//        assertEquals(25, bestilling.getBes_moms());
        assertEquals(90, bestilling.getBes_total());

    }

}