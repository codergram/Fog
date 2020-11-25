package domain.materiel;

import domain.materiel.materials.Options;
import domain.materiel.materials.Tree;
import infrastructure.DBException;

import java.util.ArrayList;

public interface MaterielRepository {

    ArrayList<Tree> getAllTræFromDB() throws DBException;

    Tree getTræById(int træ_id) throws DBException;

    ArrayList<Options> getAllBeslagSkruerFromDB() throws DBException;

    Options getBeslagSkruerById(int bes_id) throws DBException;

}
