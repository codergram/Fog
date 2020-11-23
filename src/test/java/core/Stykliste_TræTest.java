package core;

import static org.junit.jupiter.api.Assertions.*;

class Stykliste_TræTest {

    @org.junit.jupiter.api.Test
    void calculate_subtotal() {
        Træ træ = new Træ("test_product", 100, 20.5);

        String styk_bes = "Skal bruges til...";
        int antal = 2;
        Stykliste_Træ stykliste_træ = new Stykliste_Træ(træ, styk_bes, antal);
        assertEquals(41, stykliste_træ.getStyk_subtotal());
    }


}