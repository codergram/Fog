package domain.carport.shed;

import infrastructure.DBException;

public interface ShedFactory {

    Shed createSkur(Shed shed) throws DBException;

}
