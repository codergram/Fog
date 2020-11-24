package domain.materiel;

import core.materials.Options;
import core.materials.Tree;
import infrastructure.DBexception;

import java.util.ArrayList;

public interface MaterielRepository {

    ArrayList<Tree> getAllTræFromDB() throws DBexception;

    Tree getTræById(int træ_id) throws DBexception;

    ArrayList<Options> getAllBeslagSkruerFromDB() throws DBexception;

    Options getBeslagSkruerById(int bes_id) throws DBexception;

}
