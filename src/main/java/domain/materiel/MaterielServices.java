package domain.materiel;

import infrastructure.DBexception;

public interface MaterielServices {

    void updateMaterielById(int mat_id, String mat_navn, double mat_pris) throws DBexception;

}
