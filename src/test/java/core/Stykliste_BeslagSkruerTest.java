package core;

import static org.junit.jupiter.api.Assertions.*;

class Stykliste_BeslagSkruerTest {

    @org.junit.jupiter.api.Test
    void calculate_subtotal() {
        BeslagSkruer beslagSkruer = new BeslagSkruer("test_product",20);

        String styk_bes = "Skal bruges til...";
        int antal = 2;
        Stykliste_BeslagSkruer stykliste_beslagSkruer = new Stykliste_BeslagSkruer(beslagSkruer, styk_bes, antal);
        assertEquals(40, stykliste_beslagSkruer.getStyk_subtotal());
    }


}