package domain.customer;

import infrastructure.DBException;

public interface CustomerFactory {

    Customer createKunde(Customer customer) throws DBException;

}
