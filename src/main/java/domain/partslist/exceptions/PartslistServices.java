/*
 * Copyright (c) 2020. Team CoderGram
 *
 * @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
 * @author Sigurd Arik Twena Nielsen (cph-at89@cphbusiness.dk)
 * @author Jacob Lange Nielsen (cph-jn352@cphbusiness.dk)
 */

package domain.partslist.exceptions;

import domain.carport.Carport;
import domain.material.materials.Material;
import domain.partslist.Part;
import java.util.List;

public interface PartslistServices extends PartslistFactory {

  List<Part> addToPartslist(
      Carport carport, List<Material> allMaterialsFromDB, List<Part> localPartlist);
}
