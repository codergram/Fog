package domain.træ;

import core.Træ;
import infrastructure.DBexception;

public interface TræFactory {

    Træ createTræ(Træ træ) throws DBexception;

}
