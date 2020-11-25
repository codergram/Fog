package domain.materiel;

import domain.materiel.materials.Options;
import domain.materiel.materials.Tree;
import infrastructure.DBException;

public interface MaterielFactory {

    Tree createTræ(Tree materielTræ) throws DBException;

    Options createBeslagSkruer(Options materielBeslagSkruer) throws DBException;

}
