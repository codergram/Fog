package domain.materiel;

import core.materialer.Options;
import core.materialer.Tree;
import infrastructure.DBexception;

public interface MaterielFactory {

    Tree createTræ(Tree materielTræ) throws DBexception;

    Options createBeslagSkruer(Options materielBeslagSkruer) throws DBexception;

}
