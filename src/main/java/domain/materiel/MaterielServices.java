package domain.materiel;

import infrastructure.DBException;

public interface MaterielServices {

    void updateTræById(int træ_id, String træ_navn, double træ_pris) throws DBException;

    void updateBeslagSkruerById(int bes_id, String bes_navn, double bes_pris) throws DBException;
}
