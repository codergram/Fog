package domain.materiel;

import core.Materiel;
import infrastructure.DBexception;

import java.util.ArrayList;

public interface MaterielRepository {

    ArrayList<Materiel> getAllMaterielFromDB() throws DBexception;

    Materiel getMaterielById(int mat_id) throws DBexception;

}
