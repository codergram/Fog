package domain.material;

import domain.material.materials.Material;
import infrastructure.exceptions.DBException;

import java.util.List;

public interface MaterielRepository {

  List<Material> getAllMaterials() throws DBException;

  List<Material> getAllRawMaterials() throws DBException;

  void updateMaterial(int id, String name, double price, Material.Unit unit) throws DBException;
}
