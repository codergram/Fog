/*
 * Copyright (c) 2020. Team CoderGram
 *
 * @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
 * @author Sigurd Arik Twena Nielsen (cph-at89@cphbusiness.dk)
 * @author Jacob Lange Nielsen (cph-jn352@cphbusiness.dk)
 */

package domain.material;

import domain.partslist.Part;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MaterialTest {

  @Test
  void test(){
    Tree tree =
        new Tree(
            "test",
            300,
            10,
            Material.Usage.valueOf("Door"),
            Tree.Type.valueOf("Rafts"),
            Material.Unit.valueOf("Stk"));
    Part part = new Part(tree, 3, "");

    Assertions.assertEquals("Door", part.getMaterial().usage.name());

    Options options =
        new Options(
            "test",
            10,
            Material.Usage.valueOf("Door"),
            Options.Type.valueOf("Screw"),
            Material.Unit.valueOf("Stk"));
    Part part1 = new Part(options, 3, "");

    Assertions.assertEquals("Door", part.getMaterial().usage.name());
  }

}