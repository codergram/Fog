package domain.customer;

import infrastructure.exceptions.DBException;

public interface CustomerFactory {

    Customer createKunde(Customer customer) throws DBException;

}
