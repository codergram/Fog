package domain.kunde;

import core.Kunde;
import infrastructure.DBexception;

public interface KundeFactory {

    Kunde createKunde(Kunde kunde) throws DBexception;

}
