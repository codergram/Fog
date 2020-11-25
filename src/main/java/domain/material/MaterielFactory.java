package domain.material;

import domain.material.materials.Options;
import domain.material.materials.Tree;
import infrastructure.DBException;

public interface MaterielFactory {

    Tree createTræ(Tree materielTræ) throws DBException;

    Options createBeslagSkruer(Options materielBeslagSkruer) throws DBException;

}
