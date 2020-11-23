package core;

import core.materialer.Tree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Styk_TræTest {

    @Test
    void calculate_subtotal() {
        Tree materielTræ = new Tree("test_product", 100, 20.5);

        String styk_bes = "Skal bruges til...";
        int antal = 2;
        Styk_Træ styk_træ = new Styk_Træ(materielTræ, styk_bes, antal);
        assertEquals(41, styk_træ.getStyk_subtotal());
    }


}