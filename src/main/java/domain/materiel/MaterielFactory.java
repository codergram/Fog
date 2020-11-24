package domain.materiel;

import core.materials.Options;
import core.materials.Tree;
import infrastructure.DBexception;

public interface MaterielFactory {

    Tree createTræ(Tree materielTræ) throws DBexception;

    Options createBeslagSkruer(Options materielBeslagSkruer) throws DBexception;

}
