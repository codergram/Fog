package core;

import core.materials.Options;
import core.materials.Tree;
import core.parts.Part_BeslagSkruer;
import core.parts.Part_Træ;
import core.parts.Partlist;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {


    @Test
    void calculate_orderTotalOnlyTræ() {

        Tree materielTræ1 = new Tree("test_product", 100, 10);
        Tree materielTræ2 = new Tree("test_product", 200, 20);

        String styk_bes = "Skal bruges til...";
        int antal = 1;
        Part_Træ styk_træ1 = new Part_Træ(materielTræ1, styk_bes, antal);
        Part_Træ styk_træ2 = new Part_Træ(materielTræ2, styk_bes, antal);

        ArrayList<Part_Træ> styklisteTræ = new ArrayList<>();
        styklisteTræ.add(styk_træ1);
        styklisteTræ.add(styk_træ2);


        ArrayList<Part_BeslagSkruer> styk_BeslagSkruer = new ArrayList<>();


        Partlist partlist = new Partlist(styklisteTræ, styk_BeslagSkruer);

        Kunde kunde = new Kunde("", "", 0, "", 0, "");
        Carport carport = new Carport("", 0, 0);
        Skur skur = new Skur(0, 0);

        Order order = new Order("", "", 25, partlist, kunde, carport, skur);

        assertEquals(50, order.getBes_subtotal());
        assertEquals(25, order.getBes_moms());
        assertEquals(62.5, order.getBes_total());

    }

    @Test
    void calculate_orderTotalOnlyBeslagSkruer() {

        Options materielBeslagSkruer1 = new Options("test_product", 10);
        Options materielBeslagSkruer2 = new Options("test_product", 20);

        String styk_bes = "Skal bruges til...";
        int antal =2;
        Part_BeslagSkruer styk_BeslagSkruer1 = new Part_BeslagSkruer(materielBeslagSkruer1, styk_bes, antal);
        Part_BeslagSkruer styk_BeslagSkruer2 = new Part_BeslagSkruer(materielBeslagSkruer2, styk_bes, antal);

        ArrayList<Part_BeslagSkruer> styk_beslagSkruers = new ArrayList<>();
        styk_beslagSkruers.add(styk_BeslagSkruer1);
        styk_beslagSkruers.add(styk_BeslagSkruer2);


        ArrayList<Part_Træ> styk_Træ = new ArrayList<>();


        Partlist partlist = new Partlist(styk_Træ, styk_beslagSkruers);

        Kunde kunde = new Kunde("", "", 0, "", 0, "");
        Carport carport = new Carport("", 0, 0);
        Skur skur = new Skur(0, 0);

        Order order = new Order("", "", 25, partlist, kunde, carport, skur);

        assertEquals(60, order.getBes_subtotal());
        assertEquals(25, order.getBes_moms());
        assertEquals(75, order.getBes_total());

    }

    @Test
    void calculate_orderTotal() {

        Options materielBeslagSkruer1 = new Options("test_product", 10);
        Tree materielTræ1 = new Tree("test_product", 200, 20);


        String styk_bes = "Skal bruges til...";
        int antal =2;
        Part_BeslagSkruer styk_BeslagSkruer1 = new Part_BeslagSkruer(materielBeslagSkruer1, styk_bes, antal);
        Part_Træ styk_træ1 = new Part_Træ(materielTræ1, styk_bes, antal);

        ArrayList<Part_BeslagSkruer> styk_beslagSkruers = new ArrayList<>();
        styk_beslagSkruers.add(styk_BeslagSkruer1);

        ArrayList<Part_Træ> styklisteTræ = new ArrayList<>();
        styklisteTræ.add(styk_træ1);


        Partlist partlist = new Partlist(styklisteTræ, styk_beslagSkruers);

        Kunde kunde = new Kunde("", "", 0, "", 0, "");
        Carport carport = new Carport("", 0, 0);
        Skur skur = new Skur(0, 0);

        Order order = new Order("", "", 25, partlist, kunde, carport, skur);

        assertEquals(100, order.getBes_subtotal());
        assertEquals(25, order.getBes_moms());
        assertEquals(125, order.getBes_total());

    }

}