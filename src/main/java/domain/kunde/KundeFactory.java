package domain.kunde;

import core.Customer;
import infrastructure.DBexception;

public interface KundeFactory {

    Customer createKunde(Customer customer) throws DBexception;

}
