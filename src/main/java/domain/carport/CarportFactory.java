package domain.carport;

import core.Carport;
import infrastructure.DBexception;

public interface CarportFactory {

    Carport createCarport(Carport carport) throws DBexception;

}
