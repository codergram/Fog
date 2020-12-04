package domain.material;

import domain.material.materials.Material;
import infrastructure.exceptions.DBException;

public interface MaterielFactory {

    Material createMateriel(Material material) throws DBException;

//    Tree createTree(Tree tree) throws DBException;
//
//    Options createOption(Options option) throws DBException;

}
