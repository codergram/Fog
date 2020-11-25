package domain.material;

import domain.material.materials.Options;
import domain.material.materials.Tree;
import infrastructure.DBException;

import java.util.ArrayList;

public interface MaterielRepository extends MaterielFactory, MaterielServices{

    ArrayList<Tree> getAllTræFromDB() throws DBException;

    Tree getTræById(int træ_id) throws DBException;

    ArrayList<Options> getAllBeslagSkruerFromDB() throws DBException;

    Options getBeslagSkruerById(int bes_id) throws DBException;

}
