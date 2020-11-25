package core;

import domain.material.materials.Options;
import domain.parts.Part_BeslagSkruer;

import static org.junit.jupiter.api.Assertions.*;

class Part_BeslagSkruerTest {

    @org.junit.jupiter.api.Test
    void calculate_subtotal() {
        Options materielBeslagSkruer = new Options("test_product",20);

        String styk_bes = "Skal bruges til...";
        int antal = 2;
        Part_BeslagSkruer styk_beslagSkruer = new Part_BeslagSkruer(materielBeslagSkruer, styk_bes, antal);
        assertEquals(40, styk_beslagSkruer.getSubTotal());
    }


}