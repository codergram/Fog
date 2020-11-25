package domain.carport;

import infrastructure.exceptions.DBException;

public interface CarportFactory {

    Carport createCarport(Carport carport) throws DBException;

}
