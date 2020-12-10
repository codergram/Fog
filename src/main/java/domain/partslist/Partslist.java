/*
 * Copyright (c) 2020. Team CoderGram
 *
 * @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
 * @author Sigurd Arik Twena Nielsen (cph-at89@cphbusiness.dk)
 * @author Jacob Lange Nielsen (cph-jn352@cphbusiness.dk)
 */

package domain.partslist;

import domain.material.Material;
import java.util.LinkedList;
import java.util.List;

public class Partslist {
  private final List<Part> materialList;

  public Partslist() {
    materialList = new LinkedList<>();
  }

  public Partslist(List<Part> materialList) {
    this.materialList = materialList;
  }

  public void addItem(Part part) {
    if (!materialList.contains(part)) {
      materialList.add(part);
    }
  }

  public Part newPart(Material material, int amount, String description) {
    return new Part(material, amount, description);
  }

  public void removeItemByMaterial(Material material) {
    materialList.removeIf(
        i ->
            i.getMaterial().getId() == material.getId()
                && i.getMaterial().getName().equalsIgnoreCase(material.getName()));
  }

  public void removeItem(int index) {
    materialList.remove(index);
  }

  public List<Part> getPartList() {
    return materialList;
  }

  public Part getItem(int index) {
    return materialList.get(index);
  }
}
