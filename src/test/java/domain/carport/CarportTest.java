/*
 * Copyright (c) 2020. Team CoderGram
 *
 * @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
 * @author Sigurd Arik Twena Nielsen (cph-at89@cphbusiness.dk)
 * @author Jacob Lange Nielsen (cph-jn352@cphbusiness.dk)
 */

package domain.carport;

import static org.junit.jupiter.api.Assertions.*;

import domain.carport.Carport.Roof;
import domain.carport.shed.Shed;
import domain.material.Material;
import domain.material.Options;
import domain.material.Tree;
import domain.partslist.Part;
import domain.partslist.Partslist;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class CarportTest {

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

    assertEquals(90, part.getPrice());

    Options options =
        new Options(
            "test",
            10,
            Material.Usage.valueOf("Door"),
            Options.Type.valueOf("Screw"),
            Material.Unit.valueOf("Stk"));
    Part part1 = new Part(options, 3, "");

    assertEquals(30, part1.getPrice());

    List<Part> materialList = new ArrayList<>();
    materialList.add(part);
    materialList.add(part1);

    Partslist partslist = new Partslist(materialList);

    Shed shed = null;

    Carport carport = new Carport(0, 600, 300, Roof.valueOf("Peak"), shed, partslist);

    assertEquals(120, carport.getPrice());

  }

}