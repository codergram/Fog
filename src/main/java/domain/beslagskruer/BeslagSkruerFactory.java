package domain.beslagskruer;

import core.BeslagSkruer;
import infrastructure.DBexception;

public interface BeslagSkruerFactory {

    BeslagSkruer createBeslagSkruer(BeslagSkruer beslagSkruer) throws DBexception;
}
