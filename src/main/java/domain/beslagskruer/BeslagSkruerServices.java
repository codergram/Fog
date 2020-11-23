package domain.beslagskruer;

import infrastructure.DBexception;

public interface BeslagSkruerServices {

    void updateBeslagSkruerById(int bes_id, String bes_navn, double bes_pris) throws DBexception;
}
