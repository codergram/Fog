package core;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BestillingTest {


    @org.junit.jupiter.api.Test
    void calculate_orderTotalOnlyTræ() {

        Træ træ1 = new Træ("test_product", 100, 10);
        Træ træ2 = new Træ("test_product", 200, 20);

        String styk_bes = "Skal bruges til...";
        int antal = 1;
        Stykliste_Træ stykliste_træ1 = new Stykliste_Træ(træ1, styk_bes, antal);
        Stykliste_Træ stykliste_træ2 = new Stykliste_Træ(træ2, styk_bes, antal);

        ArrayList<Stykliste_Træ> styklisteTræ = new ArrayList<>();
        styklisteTræ.add(stykliste_træ1);
        styklisteTræ.add(stykliste_træ2);


        ArrayList<Stykliste_BeslagSkruer> stykliste_BeslagSkruer = new ArrayList<>();


        Stykliste stykliste = new Stykliste(styklisteTræ, stykliste_BeslagSkruer);

        Kunde kunde = new Kunde("", "", 0, "", 0, "");
        Carport carport = new Carport("", 0, 0);
        Skur skur = new Skur(0, 0);

        Bestilling bestilling = new Bestilling("", "", 25, stykliste, kunde, carport, skur);

        assertEquals(50, bestilling.getBes_subtotal());
        assertEquals(25, bestilling.getBes_moms());
        assertEquals(62.5, bestilling.getBes_total());

    }

    @org.junit.jupiter.api.Test
    void calculate_orderTotalOnlyBeslagSkruer() {

        BeslagSkruer beslagSkruer1 = new BeslagSkruer("test_product", 10);
        BeslagSkruer beslagSkruer2 = new BeslagSkruer("test_product", 20);

        String styk_bes = "Skal bruges til...";
        int antal =2;
        Stykliste_BeslagSkruer stykliste_BeslagSkruer1 = new Stykliste_BeslagSkruer(beslagSkruer1, styk_bes, antal);
        Stykliste_BeslagSkruer stykliste_BeslagSkruer2 = new Stykliste_BeslagSkruer(beslagSkruer2, styk_bes, antal);

        ArrayList<Stykliste_BeslagSkruer> stykliste_beslagSkruers = new ArrayList<>();
        stykliste_beslagSkruers.add(stykliste_BeslagSkruer1);
        stykliste_beslagSkruers.add(stykliste_BeslagSkruer2);


        ArrayList<Stykliste_Træ> stykliste_Træ = new ArrayList<>();


        Stykliste stykliste = new Stykliste(stykliste_Træ, stykliste_beslagSkruers);

        Kunde kunde = new Kunde("", "", 0, "", 0, "");
        Carport carport = new Carport("", 0, 0);
        Skur skur = new Skur(0, 0);

        Bestilling bestilling = new Bestilling("", "", 25, stykliste, kunde, carport, skur);

        assertEquals(60, bestilling.getBes_subtotal());
        assertEquals(25, bestilling.getBes_moms());
        assertEquals(75, bestilling.getBes_total());

    }

    @org.junit.jupiter.api.Test
    void calculate_orderTotal() {

        BeslagSkruer beslagSkruer1 = new BeslagSkruer("test_product", 10);
        Træ træ1 = new Træ("test_product", 200, 20);


        String styk_bes = "Skal bruges til...";
        int antal =2;
        Stykliste_BeslagSkruer stykliste_BeslagSkruer1 = new Stykliste_BeslagSkruer(beslagSkruer1, styk_bes, antal);
        Stykliste_Træ stykliste_træ1 = new Stykliste_Træ(træ1, styk_bes, antal);

        ArrayList<Stykliste_BeslagSkruer> stykliste_beslagSkruers = new ArrayList<>();
        stykliste_beslagSkruers.add(stykliste_BeslagSkruer1);

        ArrayList<Stykliste_Træ> styklisteTræ = new ArrayList<>();
        styklisteTræ.add(stykliste_træ1);


        Stykliste stykliste = new Stykliste(styklisteTræ, stykliste_beslagSkruers);

        Kunde kunde = new Kunde("", "", 0, "", 0, "");
        Carport carport = new Carport("", 0, 0);
        Skur skur = new Skur(0, 0);

        Bestilling bestilling = new Bestilling("", "", 25, stykliste, kunde, carport, skur);

        assertEquals(100, bestilling.getBes_subtotal());
        assertEquals(25, bestilling.getBes_moms());
        assertEquals(125, bestilling.getBes_total());

    }

}