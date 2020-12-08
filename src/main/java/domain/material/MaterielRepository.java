package domain.material;

import domain.material.materials.Material;
import infrastructure.exceptions.DBException;

import java.util.List;

public interface MaterielRepository extends MaterielFactory{

    List<Material> getAllMaterials() throws DBException;
    Material getMaterialById(int id) throws DBException;

    Material getMaterialByName(String name) throws DBException;
    Material updateMaterial(Material material) throws DBException;

//    List<Tree> getAllTrees() throws DBException;
//    Tree getTreeByName(String name) throws DBException;
//    Tree updateTree(Tree tree) throws DBException;
//
//    List<Options> getAllOptions() throws DBException;
//    Options getOptionByName(String name) throws DBException;
//    Options updateOption(Options option) throws DBException;
    
    boolean deleteMaterialById(int id) throws DBException;
    
    List<Material> getAllRawMaterials() throws DBException;
    
    void updateMaterial(int id, String name, double price, Material.Unit unit) throws DBException;
}
