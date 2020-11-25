package domain.material;

import domain.material.materials.Options;
import domain.material.materials.Tree;
import infrastructure.exceptions.DBException;

public interface MaterielFactory {

    Tree createTree(Tree tree) throws DBException;

    Options createOption(Options option) throws DBException;

}
