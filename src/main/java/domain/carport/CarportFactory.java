package domain.carport;

import infrastructure.DBException;

public interface CarportFactory {

    Carport createCarport(Carport carport) throws DBException;

}
