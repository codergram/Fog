package core;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

class Stykliste_MaterielTest {

    @org.junit.jupiter.api.Test
    void calculate_subtotal() {
        Materiel materiel = new Materiel("test_product", 20.5);
        int antal = 2;
        Stykliste_Materiel stykliste_materiel = new Stykliste_Materiel(materiel, antal);
        assertEquals(41, stykliste_materiel.getStyk_subtotal());
    }


}