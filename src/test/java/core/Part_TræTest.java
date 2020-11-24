package core;

import core.materials.Tree;
import core.parts.Part_Træ;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Part_TræTest {

    @Test
    void calculate_subtotal() {
        Tree materielTræ = new Tree("test_product", 100, 20.5);

        String styk_bes = "Skal bruges til...";
        int antal = 2;
        Part_Træ styk_træ = new Part_Træ(materielTræ, styk_bes, antal);
        assertEquals(41, styk_træ.getSubTotal());
    }


}