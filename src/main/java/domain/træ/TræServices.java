package domain.træ;

import infrastructure.DBexception;

public interface TræServices {

    void updateTræById(int træ_id, String træ_navn, double træ_pris) throws DBexception;

}
