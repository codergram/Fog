/*
 * Copyright (c) 2020. Team CoderGram
 *
 * @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
 * @author Sigurd Arik Twena Nielsen (cph-at89@cphbusiness.dk)
 * @author Jacob Lange Nielsen (cph-jn352@cphbusiness.dk)
 */

package domain.material;

import domain.material.materials.Material;
import infrastructure.exceptions.DBException;
import java.util.List;

public interface MaterielRepository {

  List<Material> getAllMaterials() throws DBException;

  List<Material> getAllRawMaterials() throws DBException;

  void updateMaterial(int id, String name, double price, Material.Unit unit) throws DBException;
}
