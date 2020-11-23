package core;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BestillingTest {


    @Test
    void calculate_orderTotalOnlyTræ() {

        Materiel_Træ materielTræ1 = new Materiel_Træ("test_product", 100, 10);
        Materiel_Træ materielTræ2 = new Materiel_Træ("test_product", 200, 20);

        String styk_bes = "Skal bruges til...";
        int antal = 1;
        Styk_Træ styk_træ1 = new Styk_Træ(materielTræ1, styk_bes, antal);
        Styk_Træ styk_træ2 = new Styk_Træ(materielTræ2, styk_bes, antal);

        ArrayList<Styk_Træ> styklisteTræ = new ArrayList<>();
        styklisteTræ.add(styk_træ1);
        styklisteTræ.add(styk_træ2);


        ArrayList<Styk_BeslagSkruer> styk_BeslagSkruer = new ArrayList<>();


        Stykliste stykliste = new Stykliste(styklisteTræ, styk_BeslagSkruer);

        Kunde kunde = new Kunde("", "", 0, "", 0, "");
        Carport carport = new Carport("", 0, 0);
        Skur skur = new Skur(0, 0);

        Bestilling bestilling = new Bestilling("", "", 25, stykliste, kunde, carport, skur);

        assertEquals(50, bestilling.getBes_subtotal());
        assertEquals(25, bestilling.getBes_moms());
        assertEquals(62.5, bestilling.getBes_total());

    }

    @Test
    void calculate_orderTotalOnlyBeslagSkruer() {

        Materiel_BeslagSkruer materielBeslagSkruer1 = new Materiel_BeslagSkruer("test_product", 10);
        Materiel_BeslagSkruer materielBeslagSkruer2 = new Materiel_BeslagSkruer("test_product", 20);

        String styk_bes = "Skal bruges til...";
        int antal =2;
        Styk_BeslagSkruer styk_BeslagSkruer1 = new Styk_BeslagSkruer(materielBeslagSkruer1, styk_bes, antal);
        Styk_BeslagSkruer styk_BeslagSkruer2 = new Styk_BeslagSkruer(materielBeslagSkruer2, styk_bes, antal);

        ArrayList<Styk_BeslagSkruer> styk_beslagSkruers = new ArrayList<>();
        styk_beslagSkruers.add(styk_BeslagSkruer1);
        styk_beslagSkruers.add(styk_BeslagSkruer2);


        ArrayList<Styk_Træ> styk_Træ = new ArrayList<>();


        Stykliste stykliste = new Stykliste(styk_Træ, styk_beslagSkruers);

        Kunde kunde = new Kunde("", "", 0, "", 0, "");
        Carport carport = new Carport("", 0, 0);
        Skur skur = new Skur(0, 0);

        Bestilling bestilling = new Bestilling("", "", 25, stykliste, kunde, carport, skur);

        assertEquals(60, bestilling.getBes_subtotal());
        assertEquals(25, bestilling.getBes_moms());
        assertEquals(75, bestilling.getBes_total());

    }

    @Test
    void calculate_orderTotal() {

        Materiel_BeslagSkruer materielBeslagSkruer1 = new Materiel_BeslagSkruer("test_product", 10);
        Materiel_Træ materielTræ1 = new Materiel_Træ("test_product", 200, 20);


        String styk_bes = "Skal bruges til...";
        int antal =2;
        Styk_BeslagSkruer styk_BeslagSkruer1 = new Styk_BeslagSkruer(materielBeslagSkruer1, styk_bes, antal);
        Styk_Træ styk_træ1 = new Styk_Træ(materielTræ1, styk_bes, antal);

        ArrayList<Styk_BeslagSkruer> styk_beslagSkruers = new ArrayList<>();
        styk_beslagSkruers.add(styk_BeslagSkruer1);

        ArrayList<Styk_Træ> styklisteTræ = new ArrayList<>();
        styklisteTræ.add(styk_træ1);


        Stykliste stykliste = new Stykliste(styklisteTræ, styk_beslagSkruers);

        Kunde kunde = new Kunde("", "", 0, "", 0, "");
        Carport carport = new Carport("", 0, 0);
        Skur skur = new Skur(0, 0);

        Bestilling bestilling = new Bestilling("", "", 25, stykliste, kunde, carport, skur);

        assertEquals(100, bestilling.getBes_subtotal());
        assertEquals(25, bestilling.getBes_moms());
        assertEquals(125, bestilling.getBes_total());

    }

}